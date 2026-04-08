package com.example.FirstProject.common;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@Service
public class CommonUtils {

    // Chi cho phep cac query doc du lieu de phuc vu search/tra cuu.
    private static final Set<String> READ_QUERY_PREFIXES = Set.of(
            "select",
            "show",
            "describe",
            "desc",
            "explain",
            "with"
    );

    private final JdbcTemplate jdbcTemplate;

    public CommonUtils(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Dung cho String khi can kiem tra null, rong hoac chi chua khoang trang.
    public boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    // Dung cho bien/object khi can kiem tra null hoac rong.
    public boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        }

        if (value instanceof String stringValue) {
            return stringValue.isEmpty();
        }

        if (value instanceof Collection<?> collectionValue) {
            return collectionValue.isEmpty();
        }

        if (value instanceof Map<?, ?> mapValue) {
            return mapValue.isEmpty();
        }

        if (value.getClass().isArray()) {
            return Array.getLength(value) == 0;
        }

        return false;
    }

    public Map<String, Object> executeQuery(String query) {
        String normalizedQuery = normalizeQuery(query);
        String queryPrefix = extractQueryPrefix(normalizedQuery);

        if (!READ_QUERY_PREFIXES.contains(queryPrefix)) {
            throw new RuntimeException("Hien tai chi ho tro query doc du lieu/search");
        }

        // JdbcTemplate tra ve danh sach row duoi dang key-value, phu hop de response JSON.
        List<Map<String, Object>> data = jdbcTemplate.queryForList(normalizedQuery);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("queryType", "READ");
        response.put("rowCount", data.size());
        response.put("data", data);
        return response;
    }

    private String normalizeQuery(String query) {
        if (isBlank(query)) {
            throw new RuntimeException("Query khong duoc de trong");
        }

        String normalizedQuery = query.trim();

        // Loai bo dau ';' o cuoi va chan viec gui nhieu query trong mot request.
        String withoutTrailingSemicolon = normalizedQuery.replaceAll(";+$", "").trim();
        if (withoutTrailingSemicolon.contains(";")) {
            throw new RuntimeException("Chi duoc phep thuc thi 1 query moi lan goi");
        }

        return withoutTrailingSemicolon;
    }

    private String extractQueryPrefix(String query) {
        String[] parts = query.split("\\s+", 2);
        if (parts.length == 0 || parts[0].isBlank()) {
            throw new RuntimeException("Khong xac dinh duoc loai query");
        }

        // Lay tu khoa dau tien de xac dinh day co phai query doc du lieu hay khong.
        return parts[0].toLowerCase(Locale.ROOT);
    }
}
