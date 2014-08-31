package com.example.drotatedemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Rotate extends Activity {
	/** 
     * ������ 
     */  
    private RelativeLayout layout;  
  
    /** 
     * ����չʾͼƬ�б��ListView 
     */  
    private ListView picListView;  
  
    /** 
     * ����չʾͼƬ��ϸ��ImageView 
     */  
    private ImageView picture;  
  
    /** 
     * ͼƬ�б�������� 
     */  
    private PictureAdapter adapter;  
  
    /** 
     * �������ͼƬ�ļ��� 
     */  
    private List<Picture> picList = new ArrayList<Picture>();  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        setContentView(R.layout.rotatelayout);  
        // ��ͼƬ�б����ݽ��г�ʼ������  
        initPics();  
        layout = (RelativeLayout) findViewById(R.id.layout);  
        picListView = (ListView) findViewById(R.id.pic_list_view);  
        picture = (ImageView) findViewById(R.id.picture);  
        adapter = new PictureAdapter(this, 0, picList);  
        picListView.setAdapter(adapter);  
        picListView.setOnItemClickListener(new OnItemClickListener() {  
            @Override  
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {  
                // �����ĳһ����ʱ����ImageView�е�ͼƬ����Ϊ��Ӧ����Դ  
                picture.setImageResource(picList.get(position).getResource());  
                // ��ȡ���ֵ����ĵ�λ�ã���Ϊ��ת�����ĵ�  
                float centerX = layout.getWidth() / 2f;  
                float centerY = layout.getHeight() / 2f;  
                // ����3D��ת����������ת�Ƕ�Ϊ0��90�ȣ���ʹ��ListView����ӿɼ���Ϊ���ɼ�  
                final Rotate3dAnimation rotation = new Rotate3dAnimation(0, 90, centerX, centerY,  
                        310.0f, true);  
                // ��������ʱ��500����  
                rotation.setDuration(500);  
                // ������ɺ󱣳���ɵ�״̬  
                rotation.setFillAfter(true);  
                rotation.setInterpolator(new AccelerateInterpolator());  
                // ���ö����ļ�����  
                rotation.setAnimationListener(new TurnToImageView());  
                layout.startAnimation(rotation);  
            }  
        });  
        picture.setOnClickListener(new OnClickListener() {  
            @Override  
            public void onClick(View v) {  
                // ��ȡ���ֵ����ĵ�λ�ã���Ϊ��ת�����ĵ�  
                float centerX = layout.getWidth() / 2f;  
                float centerY = layout.getHeight() / 2f;  
                // ����3D��ת����������ת�Ƕ�Ϊ360��270�ȣ���ʹ��ImageView����ӿɼ���Ϊ���ɼ���������ת�ķ������෴��  
                final Rotate3dAnimation rotation = new Rotate3dAnimation(360, 270, centerX,  
                        centerY, 310.0f, true);  
                // ��������ʱ��500����  
                rotation.setDuration(500);  
                // ������ɺ󱣳���ɵ�״̬  
                rotation.setFillAfter(true);  
                rotation.setInterpolator(new AccelerateInterpolator());  
                // ���ö����ļ�����  
                rotation.setAnimationListener(new TurnToListView());  
                layout.startAnimation(rotation);  
            }  
        });  
    }  
  
    /** 
     * ��ʼ��ͼƬ�б����ݡ� 
     */  
    private void initPics() {  
        Picture bird = new Picture("Bird", R.drawable.ic1);  
        picList.add(bird);  
        Picture winter = new Picture("Winter", R.drawable.ic2);  
        picList.add(winter);  
        Picture autumn = new Picture("Autumn", R.drawable.ic3);  
        picList.add(autumn);  
        Picture greatWall = new Picture("Great Wall", R.drawable.ic4);  
        picList.add(greatWall);  
        Picture waterFall = new Picture("Water Fall", R.drawable.ic5);  
        picList.add(waterFall);  
    }  
  
    /** 
     * ע����ListView��������еĶ������������������ListView�ĺ��������� 
     *  
     * @author guolin 
     */  
    class TurnToImageView implements AnimationListener {  
  
        @Override  
        public void onAnimationStart(Animation animation) {  
        }  
  
        /** 
         * ��ListView�Ķ�����ɺ󣬻���Ҫ������ImageView�Ķ�������ImageView�Ӳ��ɼ���Ϊ�ɼ� 
         */  
        @Override  
        public void onAnimationEnd(Animation animation) {  
            // ��ȡ���ֵ����ĵ�λ�ã���Ϊ��ת�����ĵ�  
            float centerX = layout.getWidth() / 2f;  
            float centerY = layout.getHeight() / 2f;  
            // ��ListView����  
            picListView.setVisibility(View.GONE);  
            // ��ImageView��ʾ  
            picture.setVisibility(View.VISIBLE);  
            picture.requestFocus();  
            // ����3D��ת����������ת�Ƕ�Ϊ270��360�ȣ���ʹ��ImageView����Ӳ��ɼ���Ϊ�ɼ�  
            final Rotate3dAnimation rotation = new Rotate3dAnimation(270, 360, centerX, centerY,  
                    310.0f, false);  
            // ��������ʱ��500����  
            rotation.setDuration(500);  
            // ������ɺ󱣳���ɵ�״̬  
            rotation.setFillAfter(true);  
            rotation.setInterpolator(new AccelerateInterpolator());  
            layout.startAnimation(rotation);  
        }  
  
        @Override  
        public void onAnimationRepeat(Animation animation) {  
        }  
  
    }  
  
    /** 
     * ע����ImageView��������еĶ������������������ImageView�ĺ��������� 
     *  
     * @author guolin 
     */  
    class TurnToListView implements AnimationListener {  
  
        @Override  
        public void onAnimationStart(Animation animation) {  
        }  
  
        /** 
         * ��ImageView�Ķ�����ɺ󣬻���Ҫ������ListView�Ķ�������ListView�Ӳ��ɼ���Ϊ�ɼ� 
         */  
        @Override  
        public void onAnimationEnd(Animation animation) {  
            // ��ȡ���ֵ����ĵ�λ�ã���Ϊ��ת�����ĵ�  
            float centerX = layout.getWidth() / 2f;  
            float centerY = layout.getHeight() / 2f;  
            // ��ImageView����  
            picture.setVisibility(View.GONE);  
            // ��ListView��ʾ  
            picListView.setVisibility(View.VISIBLE);  
            picListView.requestFocus();  
            // ����3D��ת����������ת�Ƕ�Ϊ90��0�ȣ���ʹ��ListView����Ӳ��ɼ���Ϊ�ɼ����Ӷ��ص�ԭ��  
            final Rotate3dAnimation rotation = new Rotate3dAnimation(90, 0, centerX, centerY,  
                    310.0f, false);  
            // ��������ʱ��500����  
            rotation.setDuration(500);  
            // ������ɺ󱣳���ɵ�״̬  
            rotation.setFillAfter(true);  
            rotation.setInterpolator(new AccelerateInterpolator());  
            layout.startAnimation(rotation);  
        }  
  
        @Override  
        public void onAnimationRepeat(Animation animation) {  
        }  
  
    }  
  
}

class Picture {  
	  
    /** 
     * ͼƬ���� 
     */  
    private String name;  
  
    /** 
     * ͼƬ�������Դ 
     */  
    private int resource;  
  
    public Picture(String name, int resource) {  
        this.name = name;  
        this.resource = resource;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public int getResource() {  
        return resource;  
    }  
  
}  

class PictureAdapter extends ArrayAdapter<Picture> {  
	  
    public PictureAdapter(Context context, int textViewResourceId, List<Picture> objects) {  
        super(context, textViewResourceId, objects);  
    }  
  
    @Override  
    public View getView(int position, View convertView, ViewGroup parent) {  
        Picture picture = getItem(position);  
        View view;  
        if (convertView == null) {  
            view = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1,  
                    null);  
        } else {  
            view = convertView;  
        }  
        TextView text1 = (TextView) view.findViewById(android.R.id.text1);  
        text1.setText(picture.getName());  
        return view;  
    }  
  
}  