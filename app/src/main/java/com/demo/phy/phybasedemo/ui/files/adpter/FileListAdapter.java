package com.demo.phy.phybasedemo.ui.files.adpter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.phy.phybasedemo.R;
import com.demo.phy.phybasedemo.data.bean.MyFile;
import com.demo.phy.phybasedemo.utils.Constant;
import com.demo.phy.phybasedemo.utils.TimeUtils;
import com.demo.phy.phybasedemo.utils.TransformUtil;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by phy on 2017/7/10.
 */

public class FileListAdapter extends BaseAdapter {


    private Context context;
    private List<MyFile> files;
    private List<MyFile> filesByPitchOn;

    public FileListAdapter(Context context, List<MyFile> files, List<MyFile> filesByPitchOn) {
        this.context = context;
        this.files = files;
        this.filesByPitchOn = filesByPitchOn;
    }

    @Override
    public int getCount() {
        return files.size();
    }

    @Override
    public Object getItem(int position) {
        return files.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    ViewHoulder houlder = null;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            houlder = new ViewHoulder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_file_list, null);
            houlder.cb_isChoose = (ImageView) convertView.findViewById(R.id.cb_isChoose);
            houlder.img_fileType = (ImageView) convertView.findViewById(R.id.img_fileType);
            houlder.tv_fileName = (TextView) convertView.findViewById(R.id.tv_fileName);
            houlder.tv_fileSize = (TextView) convertView.findViewById(R.id.tv_fileSize);
            houlder.tv_fileDate = (TextView) convertView.findViewById(R.id.tv_fileDate);
            convertView.setTag(houlder);
        } else {
            houlder = (ViewHoulder) convertView.getTag();
        }
        if (files.size() > 0) {
            final MyFile file = files.get(position);
            //显示文件名
            houlder.tv_fileName.setText(file.getFileName() + file.getFileExtension());
            //显示文件大小
            if (file.getFileSize() != null) {
                float fileSize = (float) (TransformUtil.INSTANCE.StringToInt(file.getFileSize())) / 1000;
                DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
                if (fileSize < 1000) {
                    houlder.tv_fileSize.setText(decimalFormat.format(fileSize) + "KB");
                } else if (fileSize >= 1000) {
                    houlder.tv_fileSize.setText(decimalFormat.format(fileSize / 1000) + "MB");
                }

            }
            //显示文件添加时间
            if (file.getFileAddDate() != null) {
                String date = TimeUtils.INSTANCE.unix2Date(file.getFileAddDate());
                houlder.tv_fileDate.setText(date);
            }


            switch (file.getFileExtension()) {
                case Constant.DOC:
                    houlder.img_fileType.setImageResource(R.drawable.ic_file_doc);
                    break;
                case Constant.DOCX:
                    houlder.img_fileType.setImageResource(R.drawable.ic_file_doc);
                    break;
                case Constant.XLS:
                    houlder.img_fileType.setImageResource(R.drawable.ic_file_excel);
                    break;
                case Constant.XLSX:
                    houlder.img_fileType.setImageResource(R.drawable.ic_file_excel);
                    break;
                case Constant.PPT:
                    houlder.img_fileType.setImageResource(R.drawable.ic_file_ppt);
                    break;
                case Constant.TXT:
                    houlder.img_fileType.setImageResource(R.drawable.ic_file_txt);
                    break;
                case Constant.PDF:
                    houlder.img_fileType.setImageResource(R.drawable.ic_file_pdf);
                    break;
                case Constant.APK:
                    houlder.img_fileType.setImageResource(R.drawable.ic_file_apk);
                    break;
                case Constant.MP3:
                    houlder.img_fileType.setImageResource(R.drawable.ic_file_mp3);
                    break;
                case Constant.ZIP:
                    houlder.img_fileType.setImageResource(R.drawable.ic_file_zip);
                    break;
                case Constant.PNG:
                    Uri uri = Uri.fromFile(new File(file.getFilePath()));
                    houlder.img_fileType.setImageURI(uri);
                    break;
                case Constant.JPEG:
                    uri = Uri.fromFile(new File(file.getFilePath()));
                    houlder.img_fileType.setImageURI(uri);
                    break;
                case Constant.JPG:
                    uri = Uri.fromFile(new File(file.getFilePath()));
                    houlder.img_fileType.setImageURI(uri);
                    break;
                default:
                    houlder.img_fileType.setImageResource(R.drawable.ic_file_unknow);
                    break;
            }

//        LogUtil.i("~选中情况~"+filesByPitchOn.contains(file)+"~该文件~"+file);
            if (filesByPitchOn.contains(file)) {
                houlder.cb_isChoose.setImageResource(R.drawable.chat_edit_select_on);
            } else {
                houlder.cb_isChoose.setImageResource(R.drawable.chat_edit_select_no);
            }
        }


        return convertView;
    }


    class ViewHoulder {
        private ImageView cb_isChoose;
        private ImageView img_fileType;
        private TextView tv_fileName, tv_fileSize, tv_fileDate;

    }
}
