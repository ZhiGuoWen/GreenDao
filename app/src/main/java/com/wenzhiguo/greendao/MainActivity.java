package com.wenzhiguo.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.name)
    EditText mName;
    @Bind(R.id.sex)
    EditText mSex;
    @Bind(R.id.add)
    Button mAdd;
    @Bind(R.id.delete)
    Button mDelete;
    @Bind(R.id.updata)
    Button mUpdata;
    @Bind(R.id.query)
    Button mQuery;
    @Bind(R.id.listview)
    ListView mListview;
    private LoveDao mLoveDao;
    private DaoSession mDaoSession;
    private List<Bean> mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mDaoSession = App.daoSession();
        mLoveDao = new LoveDao();
    }
    @butterknife.OnClick({R.id.add, R.id.delete, R.id.updata, R.id.query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //添加数据
            case R.id.add:
                int i = 0;
                String name = mName.getText().toString().trim();
                String sex = mSex.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(sex)) {
                    Bean bean = new Bean();
                    bean.setSex(sex);
                    bean.setName(name);
                    mLoveDao.insertLove(bean);
                    Log.d("zzz","insert"+mLoveDao.queryAll().toString());
                } else {
                    Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            //删除
            /*deleteBykey(Long key) ：根据主键删除一条记录。
            delete(User entity) ：根据实体类删除一条记录，一般结合查询方法，查询出一条记录之后删除。
            deleteAll()： 删除所有记录。*/
            case R.id.delete:
                if (mDaoSession.getBeanDao() != null) {
                    mLoveDao.deleteLove(1);
                    Log.d("zzz","delete"+mLoveDao.queryAll().toString());
                }
                break;
            //修改
            case R.id.updata:
                String name1 = mName.getText().toString().trim();
                String sex1 = mSex.getText().toString().trim();
                if (mDaoSession.getBeanDao() != null && !TextUtils.isEmpty(name1) && !TextUtils.isEmpty(sex1)) {
                    Bean bean = new Bean();
                    bean.setId((long) 1);
                    bean.setName(name1);
                    bean.setSex(sex1);
                    mLoveDao.updateLove(bean);
                    Log.d("zzz","updata"+mLoveDao.queryAll().toString());
                }
                break;
            //查询
            case R.id.query:
                if (mDaoSession.getBeanDao() != null) {
                    mBean = mLoveDao.queryAll();
                    Log.d("zzz","query"+mBean.toString());
                }
                break;
        }
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mBean.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, null);
                viewHolder.id = (TextView) view.findViewById(R.id.tv_id);
                viewHolder.name = (TextView) view.findViewById(R.id.tv_name);
                viewHolder.sex = (TextView) view.findViewById(R.id.tv_sex);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.id.setText(mBean.get(i).getId() + "");
            viewHolder.name.setText(mBean.get(i).getName() + "");
            viewHolder.sex.setText(mBean.get(i).getSex() + "");
            return view;
        }

        class ViewHolder {
            TextView id, name, sex;
        }
    }
}
