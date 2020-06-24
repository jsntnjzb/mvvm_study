package com.example.mvvm_study.ui;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.mvvm_study.R;
import com.example.mvvm_study.base.ARouterPath;
import com.example.mvvm_study.databinding.ActivityAdminMenuBinding;
import com.example.mvvm_study.viewModel.AdminMenuViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * @创建者 Chuhui
 * @创建时间 2020/6/21 13:51
 * @描述
 */
@Route(path = ARouterPath.ECAty)
public class EquipmentConfigurationActivity extends BaseActivity<ActivityAdminMenuBinding, AdminMenuViewModel> {
    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.activity_equipment_configuration;
    }

    @Override
    public int initVariableId() {
        return 0;
    }

}
