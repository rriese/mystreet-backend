package com.github.riese.rafael.mystreet.cache;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.LinkedHashMap;
import java.util.Map;

public final class DocumentCache {
    private static DocumentCache instance = new DocumentCache();
    private static Map<String, XSSFWorkbook> cache = new LinkedHashMap<>();

    private DocumentCache() {

    }

    public static DocumentCache getInstance() {
        return instance;
    }

    public void addInCache(String key, XSSFWorkbook workbook) {
        synchronized (cache) {
            cache.put(key, workbook);
        }
    }

    public XSSFWorkbook getFromCache(String key) {
        synchronized (cache) {
            return cache.get(key);
        }
    }
}
