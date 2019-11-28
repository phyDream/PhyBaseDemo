package com.demo.phy.phybasedemo.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;

import com.demo.phy.phybasedemo.data.bean.MyFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by JokAr on 16/3/29.
 */
public class FileUtils {

    /**
     * InputStrem 转byte[]
     */
    public static byte[] readStreamToBytes(InputStream in) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 8];
        int length = -1;
        while ((length = in.read(buffer)) != -1) {
            out.write(buffer, 0, length);
        }
        out.flush();
        byte[] result = out.toByteArray();
        in.close();
        out.close();
        return result;
    }

    private static String SDPATH = Environment.getExternalStorageDirectory() + "/Photo_LJ/";

    private static boolean isFileExist(String fileName) {
        File file = new File(SDPATH + fileName);
        file.isFile();
        return file.exists();
    }

    private static File createSDDir(String dirName) throws IOException {
        File dir = new File(SDPATH + dirName);
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {

            System.out.println("createSDDir:" + dir.getAbsolutePath());
            System.out.println("createSDDir:" + dir.mkdir());
        }
        return dir;
    }

    /**
     * 判断文件是否存在
     */
    public static boolean fileIsExists(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                return false;
            }

        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
        return true;
    }

    public static File createImageFile(Context context) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        //.getExternalFilesDir()方法可以获取到 SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        //创建临时文件,文件前缀不能少于三个字符,后缀如果为空默认未".tmp"
        File image = File.createTempFile(
                imageFileName,  /* 前缀 */
                ".jpg",         /* 后缀 */
                storageDir      /* 文件夹 */
        );
        String mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    public static void saveBitmap(Bitmap bm, String picName) {
        try {
            if (!isFileExist("")) {
                File tempf = createSDDir("");
            }
            File f = new File(SDPATH, picName + ".JPEG");
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入文件
     */
    public static void writeFile(InputStream in, File file) throws IOException {
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        if (file != null && file.exists())
            file.delete();
        FileOutputStream out = new FileOutputStream(file);
        byte[] buffer = new byte[1024 * 128];
        int len = -1;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        out.flush();
        out.close();
        in.close();

    }

    /**
     * 得到Bitmap的byte
     *
     * @author YOLANDA
     */
    public static byte[] bmpToByteArray(Bitmap bmp) {
        if (bmp == null)
            return null;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 80, output);

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof NinePatchDrawable) {
            Bitmap bitmap = Bitmap
                    .createBitmap(
                            drawable.getIntrinsicWidth(),
                            drawable.getIntrinsicHeight(),
                            drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return bitmap;
        } else {
            return null;
        }
    }

    /*
     * 根据view来生成bitmap图片，可用于截图功能
     */
    public static Bitmap getViewBitmap(View v) {
        v.clearFocus(); //
        v.setPressed(false); //
        // 能画缓存就返回false
        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);
        if (color != 0) {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        if (cacheBitmap == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        // Restore the view
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);
        return bitmap;
    }

    //生成文件 文件夹名- 文件名- 文件后缀类型（.png）
    public static File getFile(String dirPath, String fileName, String type) {
        File audioFile = null;
        if (SDCardUtil.isSDCardMounted()) {
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            //生成语音文件
            audioFile = new File(dir, fileName + type);
        }
        return audioFile;
    }

    //生成文件 文件夹名- 文件名- 文件后缀类型（.png）
    public static File getFile(String dirPath, String fileName) {
        File audioFile = null;
        if (SDCardUtil.isSDCardMounted()) {
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            //生成语音文件
            audioFile = new File(dir, fileName);
        }
        return audioFile;
    }

    //生成文件 文件夹名- 文件名- 文件后缀类型（.png）
    public static String getExpandedName(String fliePath) {
        int end = fliePath.lastIndexOf(".");
        if (end != -1) {
            return fliePath.substring(end, fliePath.length());
        } else {
            return null;
        }
    }

    //获得文件名
    public static String getFileName(String fliePath) {
        int start = fliePath.lastIndexOf("/");
        int end = fliePath.lastIndexOf(".");
        if (start != -1 && end != -1) {
            return fliePath.substring(start + 1, end);
        } else {
            return null;
        }
    }

    public static Intent openFile(Context context, String filePath) {
        File file = new File(filePath);
        if (!file.exists()) return null;
        /* 取得扩展名 */
        String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase();
        /* 依扩展名的类型决定MimeType */
        switch (end) {
            case "m4a":
            case "mp3":
            case "mid":
            case "xmf":
            case "ogg":
            case "wav":
                return getAudioFileIntent(context, file);
            case "3gp":
            case "mp4":
                return getVideoFileIntent(context, file);
            case "jpg":
            case "gif":
            case "png":
            case "jpeg":
            case "bmp":
                return getImageFileIntent(context, file);
            case "apk":
                return getApkFileIntent(context, file);
            case "ppt":
                return getPptFileIntent(context, file);
            case "xls":
                return getExcelFileIntent(context, file);
            case "doc":
                return getWordFileIntent(context, file);
            case "pdf":
                return getPdfFileIntent(context, file);
            case "chm":
                return getChmFileIntent(context, file);
            case "txt":
                return getTextFileIntent(context, file, false);
            case "html":
                return getHtmlFileIntent(context, file);
            default:
                return getTextFileIntent(context, file, false);
        }
    }

    //Android获取一个用于打开APK文件的intent
    public static Intent getAllIntent(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        String fileProvider = getPackageName(context) + ".fileProvider";
        Uri uri = FileProvider.getUriForFile(context, fileProvider, file);
        intent.setDataAndType(uri, "*/*");
        return intent;
    }

    //Android获取一个用于打开APK文件的intent
    private static Intent getApkFileIntent(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        return intent;
    }

    //Android获取一个用于打开VIDEO文件的intent
    private static Intent getVideoFileIntent(Context context, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "video/*");
        return intent;
    }

    //Android获取一个用于打开AUDIO文件的intent
    private static Intent getAudioFileIntent(Context context, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "audio/*");
        return intent;
    }

    //Android获取一个用于打开Html文件的intent
    private static Intent getHtmlFileIntent(Context context, File file) {
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, "text/html");
        return intent;
    }

    //Android获取一个用于打开图片文件的intent
    private static Intent getImageFileIntent(Context context, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "image/*");
        return intent;
    }

    //Android获取一个用于打开PPT文件的intent
    private static Intent getPptFileIntent(Context context, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        return intent;
    }

    //Android获取一个用于打开Excel文件的intent
    private static Intent getExcelFileIntent(Context context, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        return intent;
    }

    //Android获取一个用于打开Word文件的intent
    private static Intent getWordFileIntent(Context context, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/msword");
        return intent;
    }

    //Android获取一个用于打开CHM文件的intent
    private static Intent getChmFileIntent(Context context, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/x-chm");
        return intent;
    }

    //Android获取一个用于打开文本文件的intent
    private static Intent getTextFileIntent(Context context, File file, boolean paramBoolean) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (paramBoolean) {
            Uri uri1 = FileProvider.getUriForFile(context, getPackageName(context) + ".fileProvider", file);
            intent.setDataAndType(uri1, "text/plain");
        } else {
            Uri uri2 = Uri.fromFile(file);
            intent.setDataAndType(uri2, "text/plain");
        }
        return intent;
    }

    //Android获取一个用于打开PDF文件的intent
    private static Intent getPdfFileIntent(Context context, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/pdf");
        return intent;
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @return 当前应用的版本名称
     */
    public static synchronized String getPackageName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void inputstreamtofile(InputStream ins, File file) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[2048];
            while ((bytesRead = ins.read(buffer, 0, 2048)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制文件
     *
     * @param source 输入文件
     * @param target 输出文件
     */
    public static boolean copy(File source, File target) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(source);
            fileOutputStream = new FileOutputStream(target);
            byte[] buffer = new byte[1024];
            while (fileInputStream.read(buffer) > 0) {
                fileOutputStream.write(buffer);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //简易处理板  （实际本没有发现什么问题，可以直接使用）
    public static String getRealPathFromURI(Context context, Uri contentURI) {
        String result;
        Cursor cursor = context.getContentResolver().query(contentURI,
                new String[]{MediaStore.Images.ImageColumns.DATA},//
                null, null, null);
        if (cursor == null) result = contentURI.getPath();
        else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(index);
            cursor.close();
        }
        return result;
    }

    //复杂版处理  (适配多种API)   最后直接调用这个方法就可以了
    public static String getRealPathFromUri(Context context, Uri uri) {
        int sdkVersion = Build.VERSION.SDK_INT;
        if (sdkVersion < 11) return getRealPathFromUri_BelowApi11(context, uri);
        if (sdkVersion < 19) return getRealPathFromUri_Api11To18(context, uri);
        else return getRealPathFromUri_AboveApi19(context, uri);
    }

    /**
     * 适配api19以上,根据uri获取图片的绝对路径
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static String getRealPathFromUri_AboveApi19(Context context, Uri uri) {
        String filePath = null;
        String wholeID = DocumentsContract.getDocumentId(uri);

        // 使用':'分割
        String id = wholeID.split(":")[1];

        String[] projection = {MediaStore.Images.Media.DATA};
        String selection = MediaStore.Images.Media._ID + "=?";
        String[] selectionArgs = {id};

        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,//
                projection, selection, selectionArgs, null);
        int columnIndex = cursor.getColumnIndex(projection[0]);
        if (cursor.moveToFirst()) filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

    /**
     * 适配api11-api18,根据uri获取图片的绝对路径
     */
    private static String getRealPathFromUri_Api11To18(Context context, Uri uri) {
        String filePath = null;
        String[] projection = {MediaStore.Images.Media.DATA};
        //这个有两个包不知道是哪个。。。。不过这个复杂版一般用不到
        CursorLoader loader = new CursorLoader(context, uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();

        if (cursor != null) {
            cursor.moveToFirst();
            filePath = cursor.getString(cursor.getColumnIndex(projection[0]));
            cursor.close();
        }
        return filePath;
    }

    /**
     * 适配api11以下(不包括api11),根据uri获取图片的绝对路径
     */
    private static String getRealPathFromUri_BelowApi11(Context context, Uri uri) {
        String filePath = null;
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            filePath = cursor.getString(cursor.getColumnIndex(projection[0]));
            cursor.close();
        }
        return filePath;
    }

    /**
     * 获取指定文件大小
     */
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            Log.e("","文件不存在!");
        }
        return size;
    }

    /**
     * 通过Uri获取文件在本地存储的真实路径
     */
    public static String getRealPathFromURI(Activity act, Uri contentUri) {
        // can post image
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = act.managedQuery(contentUri, proj, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        }
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    /**
     * 文件在本地存储的真实路径获取MimeType
     */
    public static String getMimeType(String filePath) {
        String ext = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();
        if (!TextUtils.isEmpty(ext)) {
            ext = ext.substring(1);
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext);
    }

    /**
     * 根据uri获取视频时长
     */
    public static long getVideoDurationByUri(Context context, Uri uri) {
        long result = 0L;
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                result = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
            }
            cursor.close();
        }
        return result;
    }

    //获得某类型的文件
    public static List<MyFile> getSpecificTypeOfFile(Context context, String[] extension)
    {
        List<MyFile> files = new ArrayList<>();//文件数据集合
        //从外存中获取
        Uri fileUri= MediaStore.Files.getContentUri("external");
        //筛选列，这里只筛选了：文件路径和不含后缀的文件名
        String[] projection=new String[]{
                MediaStore.Files.FileColumns.DATA, MediaStore.Files.FileColumns.TITLE, MediaStore.Files.FileColumns.SIZE, MediaStore.Files.FileColumns.DATE_ADDED, MediaStore.Files.FileColumns.MIME_TYPE
        };
        //构造筛选语句
        String selection="";
        for(int i=0;i<extension.length;i++)
        {
            if(i!=0)
            {
                selection=selection+" OR ";
            }
            selection=selection+ MediaStore.Files.FileColumns.DATA+" LIKE '%"+extension[i]+"'";
        }
//        LogUtil.i("~筛选语句~"+selection);
        //按时间递增顺序对结果进行排序;待会从后往前移动游标就可实现时间递减
        String sortOrder= MediaStore.Files.FileColumns.DATE_MODIFIED;
        //获取内容解析器对象
        ContentResolver resolver=context.getContentResolver();
        //获取游标
        Cursor cursor=resolver.query(fileUri, projection, selection, null, sortOrder);
        if(cursor==null)
            return files;
        //游标从最后开始往前递减，以此实现时间递减顺序（最近访问的文件，优先显示）
        if(cursor.moveToLast())
        {
            do{
                //输出文件的完整路径
                String fliePath=cursor.getString(0);
                String title=cursor.getString(1);
                String size=cursor.getString(2);
                String addDate=cursor.getString(3);
                String mime_type=cursor.getString(4);
//                LogUtil.i("~文件路径~"+fliePath+"~文件名~"+title+"~SIZE~"+size+"~DATE_ADDED~"+addDate+"~MIME_TYPE~"+mime_type);

                MyFile file = new MyFile();
                file.setFilePath(fliePath);
                file.setFileName(title);
                file.setFileSize(size);
                file.setFileAddDate(addDate);
                file.setFileExtension(getExpandedName(fliePath));
                files.add(file);
            }while(cursor.moveToPrevious());

        }
        cursor.close();
        return files;
    }

}
