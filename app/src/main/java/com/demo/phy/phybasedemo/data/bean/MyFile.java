package com.demo.phy.phybasedemo.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by phy on 2019/11/27
 */
public class MyFile implements Parcelable{
    private String fileName;
    private String filePath;
    private String fileExtension;//扩展名
    private String fileSize;
    private String fileAddDate;//添加时间
    private String downloadPath;//下载在本地的存储位置

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileAddDate() {
        return fileAddDate;
    }

    public void setFileAddDate(String fileAddDate) {
        this.fileAddDate = fileAddDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyFile file = (MyFile) o;

        if (fileName != null ? !fileName.equals(file.fileName) : file.fileName != null)
            return false;
        if (filePath != null ? !filePath.equals(file.filePath) : file.filePath != null)
            return false;
        if (fileExtension != null ? !fileExtension.equals(file.fileExtension) : file.fileExtension != null)
            return false;
        if (fileSize != null ? !fileSize.equals(file.fileSize) : file.fileSize != null)
            return false;
        return fileAddDate != null ? fileAddDate.equals(file.fileAddDate) : file.fileAddDate == null;

    }

    @Override
    public int hashCode() {
        int result = fileName != null ? fileName.hashCode() : 0;
        result = 31 * result + (filePath != null ? filePath.hashCode() : 0);
        result = 31 * result + (fileExtension != null ? fileExtension.hashCode() : 0);
        result = 31 * result + (fileSize != null ? fileSize.hashCode() : 0);
        result = 31 * result + (fileAddDate != null ? fileAddDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MyFile{" +
                "fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", fileAddDate='" + fileAddDate + '\'' +
                ", downloadPath='" + downloadPath + '\'' +
                '}';
    }

    public MyFile() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fileName);
        dest.writeString(this.filePath);
        dest.writeString(this.fileExtension);
        dest.writeString(this.fileSize);
        dest.writeString(this.fileAddDate);
        dest.writeString(this.downloadPath);
    }

    protected MyFile(Parcel in) {
        this.fileName = in.readString();
        this.filePath = in.readString();
        this.fileExtension = in.readString();
        this.fileSize = in.readString();
        this.fileAddDate = in.readString();
        this.downloadPath = in.readString();
    }

    public static final Creator<MyFile> CREATOR = new Creator<MyFile>() {
        @Override
        public MyFile createFromParcel(Parcel source) {
            return new MyFile(source);
        }

        @Override
        public MyFile[] newArray(int size) {
            return new MyFile[size];
        }
    };
}
