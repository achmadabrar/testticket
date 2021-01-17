package com.achmadabrar.tickettest.presentation.activities

import android.os.Bundle
import com.achmadabrar.tickettest.R
import com.achmadabrar.tickettest.core.base.BaseActivity
import com.achmadabrar.tickettest.presentation.fragments.SearchUserFragment

class SearchUserActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_user)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, SearchUserFragment())
        transaction.commit()
    }
}