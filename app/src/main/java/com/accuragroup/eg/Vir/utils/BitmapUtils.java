package com.accuragroup.eg.Vir.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import com.accuragroup.eg.Vir.Const;
import com.accuragroup.eg.Vir.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by Karam on 11/4/2015.
 * A utility class for resizing, downscaling, fixing orientation and saving bitmaps.
 */
public class BitmapUtils {

    /**
     * creates a file with the specified name in the external storage directory.
     *
     * @param ctx      the context
     * @param fileName the file name to create
     * @return the file if created, or null otherwise
     */
    public static File createImagePath(Context ctx, String fileName) {

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Utils.showLongToast(ctx, R.string.unplug_mobile_cable_from_computer);
            return null;
        }

        File directoryPath = new File(Environment.getExternalStorageDirectory().getPath() + Const.APP_FILES_DIR + "/images");

        if (!directoryPath.exists()) {
            if (!directoryPath.mkdirs()) {
                return null;
            }
        }

        File filePath = new File(directoryPath + String.format("/%s.jpg", fileName));
        return filePath;
    }

    /**
     * Deletes all images in the images directory.
     */
    public static void cleanImagesDirectory() {

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return;
        }

        File imagesDirectory = new File(Environment.getExternalStorageDirectory().getPath() + Const.APP_FILES_DIR + "/images");

        if (imagesDirectory.exists() && imagesDirectory.isDirectory()) {
            for (File file : imagesDirectory.listFiles()) {
                if (!file.isDirectory()) file.delete();
            }
        }
    }

    /**
     * method, used to resize a bitmap keeping the same ratio and does'nt lose its ratios
     *
     * @param bitmapFilePath
     * @param maxDimen
     * @return
     */
    public static Bitmap resizeBitmap(String bitmapFilePath, int maxDimen) {
        Bitmap bitmap = BitmapFactory.decodeFile(bitmapFilePath);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        if (width > height) {
            if (width > maxDimen) {
                float ratio = (float) maxDimen / (float) width;
                width = maxDimen;
                height = (int) ((float) height * ratio);
            }
        } else {
            if (height > maxDimen) {
                float ratio = (float) maxDimen / (float) height;
                height = maxDimen;
                width = (int) ((float) width * ratio);
            }
        }

        return resizeBitmap(bitmapFilePath, width, height);
    }

    /**
     * Resize the bitmap which located in the specified path and respecting its aspect ratio, rotate it by Exif rotation and save it in place.
     *
     * @param bitmapFilePath
     * @param width
     * @param height
     * @return the resized bitmap if successfully resized or null.
     */
    public static Bitmap resizeBitmap(String bitmapFilePath, int width, int height) {
        Bitmap bitmap = BitmapFactory.decodeFile(bitmapFilePath);

        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();

        int newWidth = -1;
        int newHeight = -1;
        float multFactor = -1.0F;

        if (!(originalWidth < width && originalHeight < height)) { //don't scale up the bitmap
            if (originalHeight > originalWidth) {
                newHeight = height;
                multFactor = (float) originalWidth / (float) originalHeight;
                newWidth = (int) (newHeight * multFactor);
            } else if (originalWidth > originalHeight) {
                newWidth = width;
                multFactor = (float) originalHeight / (float) originalWidth;
                newHeight = (int) (newWidth * multFactor);
            } else if (originalHeight == originalWidth) {
                newHeight = height;
                newWidth = width;
            }
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
            bitmap.recycle();
            bitmap = resizedBitmap;
        }


        //resizing is before rotation for not consuming large space when rotating

        //handling exif rotation
        //http://stackoverflow.com/a/4105966
        //for more: https://gist.github.com/9re/1990019
        try {
            ExifInterface exifData = new ExifInterface(bitmapFilePath);
            int orientation = exifData.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            Utils.logE("Orientation: " + orientation);

            Matrix matrix = new Matrix();
            Bitmap rotatedBitmap = null;
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    matrix.postRotate(90);
                    rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    matrix.postRotate(180);
                    rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    matrix.postRotate(270);
                    rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                    break;
            }

            if (rotatedBitmap != null) { //bitmap has been rotated
                bitmap.recycle();
                bitmap = rotatedBitmap;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        saveBitmap(bitmap, bitmapFilePath, 80);

        return bitmap;
    }


    /**
     * Resize the bitmap which located in the specified originalUri and respecting its aspect ratio, rotate it by Exif rotation and save it in  a new path with the specified newFileName.
     *
     * @param originalUri
     * @param newFileName the name of the resized bitmap file
     * @param width
     * @param height
     * @return the resized bitmap File path or null.
     */
    public static File resizeBitmap(Context ctx, Uri originalUri, String newFileName, int width, int height) {

        InputStream inputStream;
        Bitmap bitmap;
        try {
            inputStream = ctx.getContentResolver().openInputStream(originalUri);
            bitmap = BitmapFactory.decodeStream(inputStream);
            if (inputStream != null)
                inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        String originalPath = FileUtils.getPath(ctx, originalUri); //the actual image path
        Utils.logE("path: ====== " + originalPath);
        if (originalPath == null)
            return null;


        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();

        int newWidth = -1;
        int newHeight = -1;
        float multFactor = -1.0F;

        if (!(originalWidth < width && originalHeight < height)) { //don't scale up the bitmap
            if (originalHeight > originalWidth) {
                newHeight = height;
                multFactor = (float) originalWidth / (float) originalHeight;
                newWidth = (int) (newHeight * multFactor);
            } else if (originalWidth > originalHeight) {
                newWidth = width;
                multFactor = (float) originalHeight / (float) originalWidth;
                newHeight = (int) (newWidth * multFactor);
            } else if (originalHeight == originalWidth) {
                newHeight = height;
                newWidth = width;
            }
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
            bitmap.recycle();
            bitmap = resizedBitmap;
        }


        //resizing is before rotation for not consuming large space when rotating

        //handling exif rotation
        //http://stackoverflow.com/a/4105966
        //for more: https://gist.github.com/9re/1990019
        try {
            ExifInterface exifData = new ExifInterface(originalPath);
            int orientation = exifData.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            Utils.logE("Orientation: " + orientation);

            Matrix matrix = new Matrix();
            Bitmap rotatedBitmap = null;
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    matrix.postRotate(90);
                    rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    matrix.postRotate(180);
                    rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    matrix.postRotate(270);
                    rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                    break;
            }

            if (rotatedBitmap != null) { //bitmap has been rotated
                bitmap.recycle();
                bitmap = rotatedBitmap;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        File newPath = createImagePath(ctx, newFileName);
        if (newPath != null)
            saveBitmap(bitmap, newPath.getAbsolutePath(), 80);

        return newPath;
    }

    /**
     * Saves the bitmap to the specified path with the specified quality.
     *
     * @param bitmapFilePath
     * @param bitmap
     * @param quality
     * @return True if successfully saved.
     */
    public static boolean saveBitmap(Bitmap bitmap, String bitmapFilePath, int quality) {
        File imageFile = new File(bitmapFilePath);

        try {
            FileOutputStream out = new FileOutputStream(imageFile);
            Boolean compressed = bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out);
            out.flush();
            out.close();
            return compressed;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String encodeBase64(File image) {

        Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath());
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
        byte[] byteArr = os.toByteArray();
        return Base64.encodeToString(byteArr, Base64.DEFAULT);
    }

    public static Bitmap decodeBase64(String encodedImage) {
        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    public static String getStringImage(File image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Bitmap bmp = BitmapFactory.decodeFile(image.getAbsolutePath());

        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = null;
        if (imageBytes.length > 512000 && imageBytes.length <= 2048000) {
            bmp.compress(Bitmap.CompressFormat.JPEG, 70, baos);
            imageBytes = baos.toByteArray();
            encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        } else if (imageBytes.length > 2048000) {
            encodedImage = "exceeds";
        } else {

            encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        }


        return encodedImage;
    }


    public static Bitmap drawTextToBitmap(Context gContext,
                                          Bitmap gResId,
                                          String gText) {
        Resources resources = gContext.getResources();
        float scale = resources.getDisplayMetrics().density;

        Bitmap.Config bitmapConfig =
                gResId.getConfig();
        // set default bitmap config if none
        if (bitmapConfig == null) {
            bitmapConfig = Bitmap.Config.ARGB_8888;
        }
        // resource bitmaps are imutable,
        // so we need to convert it to mutable one
        gResId = gResId.copy(bitmapConfig, true);

        Canvas canvas = new Canvas(gResId);
        // new antialised Paint
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // text color - #3D3D3D
        paint.setColor(Color.CYAN);
        // text size in pixels
        paint.setTextSize((int) (17 * scale));
        // text shadow
        paint.setShadowLayer(1f, 0f, 1f, Color.RED);

        // draw text to the Canvas center
        Rect bounds = new Rect();
        paint.getTextBounds(gText, 0, gText.length(), bounds);
        int x = (gResId.getWidth() - bounds.width()) / 2;
        int y = (gResId.getHeight() + bounds.height()) / 2;

        canvas.drawText(gText, x, y, paint);

        return gResId;
    }



    private Target picassoImageTarget(Context context, final String imageDir, final String imageName) {
        Log.d("picassoImageTarget", " picassoImageTarget");
        ContextWrapper cw = new ContextWrapper(context);
        final File directory = cw.getDir(imageDir, Context.MODE_PRIVATE); // path to /data/data/yourapp/app_imageDir
        return new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final File myImageFile = new File(directory, imageName); // Create image file
                        FileOutputStream fos = null;
                        try {
                            fos = new FileOutputStream(myImageFile);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.i("image", "image saved to >>>" + myImageFile.getAbsolutePath());

                    }
                }).start();
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
            }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                if (placeHolderDrawable != null) {}
            }
        };
    }

}
