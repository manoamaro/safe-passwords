package br.com.manoel.amaro.safepasswords.domain;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import br.com.manoel.amaro.safepasswords.annotation.Column;
import br.com.manoel.amaro.safepasswords.annotation.Entity;

/**
 * Created by manoel on 26/09/14.
 */
public abstract class AbstractDao<T extends PersistableEntity> {

    private static SQLiteOpenHelper helper;
    private Class<T> clazz;
    private List<Field> entityFields;

    public AbstractDao(Class<T> clazz) {
        this.clazz = clazz;
        this.entityFields = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class))
                this.entityFields.add(field);
        }

    }

    public static void setHelper(SQLiteOpenHelper sqLiteOpenHelper) {
        helper = sqLiteOpenHelper;
    }

    public Long persist(T entity) {
        SQLiteDatabase database = helper.getWritableDatabase();
        Long id = database.insert(this.getTableName(), null, this.buildValues(entity));
        database.close();
        return id;
    }

    public List<T> findAll() {
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.query(this.getTableName(), null, null, null, null, null, "id asc");
        cursor.moveToFirst();
        database.close();
        return this.buildResultFromCursor(cursor);
    }

    public List<T> query(String sql, String... args) {
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, args);
        database.close();
        return this.buildResultFromCursor(cursor);
    }

    private List<T> buildResultFromCursor(Cursor cursor) {
        List<T> result = new ArrayList<T>();
        while (cursor.moveToNext()) {
            result.add(this.buildObject(cursor));
        }
        return result;
    }

    public String getTableName() {
        Entity entityAnnotation = clazz.getAnnotation(Entity.class);
        return "".equals(entityAnnotation.tableName()) ?
                clazz.getSimpleName() : entityAnnotation.tableName();
    }

    public Long count() {
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery(String.format("SELECT COUNT(1) FROM %s", getTableName()),
                new String[]{});
        cursor.moveToNext();
        database.close();
        return cursor.getLong(0);
    }

    protected ContentValues buildValues(T entity) {
        ContentValues contentValues = new ContentValues();
        try {
            for (Field field : this.entityFields) {
                field.setAccessible(true);
                Column column = field.getAnnotation(Column.class);
                String columnName = "".equals(column.name()) ? field.getName() : column.name();
                if (field.getType().equals(Long.class))
                    contentValues.put(columnName, (Long) field.get(entity));
                if (field.getType().equals(Integer.class))
                    contentValues.put(columnName, (Integer) field.get(entity));
                if (field.getType().equals(String.class))
                    contentValues.put(columnName, (String) field.get(entity));
                field.setAccessible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contentValues;
    }

    protected T buildObject(Cursor cursor) {
        try {
            T entity = clazz.newInstance();
            for (Field field : this.entityFields) {
                field.setAccessible(true);
                Column column = field.getAnnotation(Column.class);
                String columnName = "".equals(column.name()) ? field.getName() : column.name();
                if (field.getType().equals(Long.class))
                    field.set(entity, cursor.getLong(cursor.getColumnIndex(columnName)));
                if (field.getType().equals(Integer.class))
                    field.set(entity, cursor.getInt(cursor.getColumnIndex(columnName)));
                if (field.getType().equals(String.class))
                    field.set(entity, cursor.getString(cursor.getColumnIndex(columnName)));
                field.setAccessible(false);
            }
            return entity;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
