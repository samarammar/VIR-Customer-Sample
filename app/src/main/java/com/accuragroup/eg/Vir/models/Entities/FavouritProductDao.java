package com.accuragroup.eg.Vir.models.Entities;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "FAVOURIT_PRODUCT".
*/
public class FavouritProductDao extends AbstractDao<FavouritProduct, Long> {

    public static final String TABLENAME = "FAVOURIT_PRODUCT";

    /**
     * Properties of entity FavouritProduct.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property Img = new Property(1, String.class, "img", false, "IMG");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property Owner = new Property(3, String.class, "owner", false, "OWNER");
        public final static Property Price = new Property(4, int.class, "price", false, "PRICE");
        public final static Property City = new Property(5, String.class, "city", false, "CITY");
        public final static Property Gov = new Property(6, String.class, "gov", false, "GOV");
        public final static Property Cat = new Property(7, String.class, "cat", false, "CAT");
    }


    public FavouritProductDao(DaoConfig config) {
        super(config);
    }
    
    public FavouritProductDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FAVOURIT_PRODUCT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "\"IMG\" TEXT," + // 1: img
                "\"TITLE\" TEXT," + // 2: title
                "\"OWNER\" TEXT," + // 3: owner
                "\"PRICE\" INTEGER NOT NULL ," + // 4: price
                "\"CITY\" TEXT," + // 5: city
                "\"GOV\" TEXT," + // 6: gov
                "\"CAT\" TEXT);"); // 7: cat
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FAVOURIT_PRODUCT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, FavouritProduct entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String img = entity.getImg();
        if (img != null) {
            stmt.bindString(2, img);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String owner = entity.getOwner();
        if (owner != null) {
            stmt.bindString(4, owner);
        }
        stmt.bindLong(5, entity.getPrice());
 
        String city = entity.getCity();
        if (city != null) {
            stmt.bindString(6, city);
        }
 
        String gov = entity.getGov();
        if (gov != null) {
            stmt.bindString(7, gov);
        }
 
        String cat = entity.getCat();
        if (cat != null) {
            stmt.bindString(8, cat);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, FavouritProduct entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String img = entity.getImg();
        if (img != null) {
            stmt.bindString(2, img);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String owner = entity.getOwner();
        if (owner != null) {
            stmt.bindString(4, owner);
        }
        stmt.bindLong(5, entity.getPrice());
 
        String city = entity.getCity();
        if (city != null) {
            stmt.bindString(6, city);
        }
 
        String gov = entity.getGov();
        if (gov != null) {
            stmt.bindString(7, gov);
        }
 
        String cat = entity.getCat();
        if (cat != null) {
            stmt.bindString(8, cat);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public FavouritProduct readEntity(Cursor cursor, int offset) {
        FavouritProduct entity = new FavouritProduct( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // img
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // title
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // owner
            cursor.getInt(offset + 4), // price
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // city
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // gov
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // cat
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, FavouritProduct entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setImg(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setOwner(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPrice(cursor.getInt(offset + 4));
        entity.setCity(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setGov(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setCat(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(FavouritProduct entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(FavouritProduct entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(FavouritProduct entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}