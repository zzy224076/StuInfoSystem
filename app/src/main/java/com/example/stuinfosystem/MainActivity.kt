package com.example.stuinfosystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userDao =AppDataBase.getDatabase(this).userDao()
        var loginButton:Button?=null
        val user1=User(1002,"123456","张三",0)
        val user2= User(1001,"123456","admin",0)
        var idText:TextView? =null
        idText = findViewById(R.id.idText)
        var password:TextView?=null
        password =findViewById(R.id.passwordText)
        loginButton=findViewById(R.id.loginButton)
        loginButton.setOnClickListener{
            thread {
                Looper.prepare();
                //userDao.insert(user1)
                //userDao.insert(user2)
                var id:String = idText.getText().toString()
                var passwor:String = password.getText().toString()
                if(id==""||passwor==""){
                    Toast.makeText(this,"请填写完整！！！",Toast.LENGTH_SHORT).show()
                }else{
                    var user:User = userDao.selectOneByNameAndPassword(id.toInt(),passwor)
                    if (user==null){
                        Toast.makeText(this,"用户名或密码错误",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show()
                        // 页面跳转
                        var intent = Intent()
                        intent.setClass(this,AdminActivity().javaClass)
                        //var userList:List<User> = userDao.selectAll()

                        intent.putExtra("userName", user.name)
                        startActivity(intent)
                    }
                }

                Looper.loop();
            }
        }
//        val user1=User(1001,"123456",0)
//         userDao.insert(user1)
//
//        var loginButton:Button?=null
//
//        loginButton=findViewById(R.id.loginButton)
//        idText = findViewById(R.id.idText)
//        loginButton?.setOnClickListener{
//            var text:String=idText.getText().toString()
//            Toast.makeText(this, text,Toast.LENGTH_SHORT).show()
//
//                var user:User=userDao.selectOneByNameAndPassword(1001,"123456")
//                if (user==null){
//                Toast.makeText(this,"用户名或密码错误",Toast.LENGTH_SHORT).show()
//            }else{
//               Toast.makeText(this,"用户名或密码错误",Toast.LENGTH_SHORT).show()
//            }
//
//
//
//
//        }

    }
}


