package com.ako.mobilesmarket.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ako.mobilesmarket.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.profile.*
import kotlinx.android.synthetic.main.signup.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.uiThread

class AccountFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_account, container, false)

        doAsync {
            uiThread {
                val ani=AnimationUtils.loadAnimation(context,R.anim.updown)
                val down=AnimationUtils.loadAnimation(context,R.anim.down)
                if(FirebaseAuth.getInstance().currentUser == null){
                    loginlayout.visibility=View.VISIBLE
                    signuplayout.visibility=View.GONE
                    profilelayout.visibility=View.GONE
                    signup.setOnClickListener {
                        signuplayout.visibility=View.VISIBLE
                        profilelayout.visibility=View.GONE
                        loginlayout.visibility=View.GONE
                        login.setOnClickListener {
                            loginlayout.visibility=View.VISIBLE
                            signuplayout.visibility=View.GONE
                            profilelayout.visibility=View.GONE
                        }
                    }

                }else{
                    profilelayout.visibility=View.VISIBLE
                    logout.setOnClickListener {
                        FirebaseAuth.getInstance().signOut()
                        loginlayout.visibility=View.VISIBLE
                        signuplayout.visibility=View.GONE
                        profilelayout.visibility=View.GONE
                    }
                }
                signupbuttom.setOnClickListener {
                    if(signupemail.length()<1 || singuppassward.length()<1){
                        toast("Enter email and passward")
                        return@setOnClickListener
                    }
                  FirebaseAuth.getInstance()
                      .createUserWithEmailAndPassword(signupemail.text.toString(),singuppassward.text.toString())
                      .addOnCompleteListener {
                          if(it.isSuccessful){
                              profilelayout.visibility=View.VISIBLE
                              loginlayout.visibility=View.GONE
                              signuplayout.visibility=View.GONE
                              toast("Sign up successful with ${signupemail.text}")
                          }
                      }
                      .addOnFailureListener {
                          Log.d("checkerror",it.message)
                      }
                }
                loginbutton.setOnClickListener {
                    if(loginemail.length()<1 || loginpassward.length()<1){
                        toast("Enter email and passward")
                        return@setOnClickListener
                    }
                    FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(loginemail.text.toString(),loginpassward.text.toString())
                        .addOnCompleteListener {
                            if(it.isSuccessful){
                                profilelayout.visibility=View.VISIBLE
                                signuplayout.visibility=View.GONE
                                loginlayout.visibility=View.GONE
                                toast("Login in successful ")
                            }
                        }
                        .addOnFailureListener {
                            toast("Email or passward incorrect")
                        }
                }
            }
        }
        return root
    }
}