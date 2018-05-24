package com.hello.d.studentsystem;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    EditText grade,name,delectnum;
    StudentManager sm;
    SQLiteDatabase sdb;
    ListView result;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grade= (EditText) findViewById(R.id.grade);
        name= (EditText) findViewById(R.id.name);
        result= (ListView) findViewById(R.id.result);
        delectnum= (EditText) findViewById(R.id.delectnum);

        sm=new StudentManager(this,"student.db",null,1);
        sdb=sm.getReadableDatabase();


    }

    private void edittextClean()
    {
        delectnum.setText("");
        name.setText("");
        grade.setText("");
    }

    public void add(View view)
    {
        sdb.execSQL("insert into student_tb values(null,?,?)",new String[]{name.getText().toString(),grade.getText().toString()});
        edittextClean();
        Toast.makeText(this,"添加成功",Toast.LENGTH_SHORT).show();
    }

    public void delect(View view)
    {
        sdb.execSQL("delete from student_tb where _id = ?",new String[]{delectnum.getText().toString()});
        edittextClean();
        Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show();
    }
    public void update(View view)
    {
        sdb.execSQL("update student_tb set  name = ? ,grade = ? where  _id = ?",
                new String[]{name.getText().toString(),grade.getText().toString(),delectnum.getText().toString()});
        edittextClean();
        Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
    }

    public void query(View view)
    {
        Cursor cursor=sdb.rawQuery("select * from student_tb",null);
        SimpleCursorAdapter simpleCursorAdapter=new SimpleCursorAdapter(this,R.layout.item,cursor,
                new String[]{"_id","name","grade"},new int[]{R.id.inum,R.id.iname,R.id.igrade}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        result.setAdapter(simpleCursorAdapter);
    }


    protected void onDestroy()
    {
        super.onDestroy();
        if(sdb!=null)
        {
            sm.close();;
            sm.close();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
