<template>
  <div id="container-demo">
    <el-row>
      <el-col :span="4">
        <el-container>
          <el-header>Header</el-header>
          <el-main>Main</el-main>
        </el-container>
      </el-col>
      <el-col :span="4">
        <el-container>
          <el-header>Header</el-header>
          <el-main>Main</el-main>
          <el-footer>Footer</el-footer>
        </el-container>
      </el-col>
      <el-col :span="4">
        <el-container>
          <el-aside width="100px">Aside</el-aside>
          <el-main>Main</el-main>
        </el-container>
      </el-col>
      <el-col :span="4">
        <el-container>
          <el-header>Header</el-header>
          <el-container>
            <el-aside width="100px">Aside</el-aside>
            <el-main>Main</el-main>
          </el-container>
        </el-container>
      </el-col>
      <el-col :span="4">
        <el-container>
          <el-header>Header</el-header>
          <el-container>
            <el-aside width="100px">Aside</el-aside>
            <el-container>
              <el-main>Main</el-main>
              <el-footer>Footer</el-footer>
            </el-container>
          </el-container>
        </el-container>
      </el-col>
    </el-row>
    <el-container style="height: 500px; border: 1px solid #eee;">
      <el-aside style="background-color: rgb(238,241,246)" width="200px">
        <el-menu :default-openeds="['1', '3']">
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-message">导航一</i>
            </template>
            <el-menu-item-group>
              <template slot="title"
                >分组一</template
              >
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组二">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title"
                >选项4</template
              >
              <el-menu-item index="1-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-menu"></i>导航二
            </template>
            <el-menu-item-group>
              <template slot="title"
                >分组一</template
              >
              <el-menu-item index="2-1">选项1</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="2-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="2-4">
              <template slot="title"
                >选项4</template
              >
              <el-menu-item index="2-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-setting"></i>导航三
            </template>
            <el-menu-item-group>
              <template slot="title"
                >分组一</template
              >
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="3-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="3-4">
              <template slot="title"
                >选项4</template
              >
              <el-menu-item index="3-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header style="text-align: right;font-size: 12px">
          <el-dropdown>
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>查看</el-dropdown-item>
              <el-dropdown-item>新增</el-dropdown-item>
              <el-dropdown-item>删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <span>王小虎</span>
        </el-header>
        <el-main>
          <el-table :data="tableData">
            <el-table-column
              label="日期"
              prop="date"
              width="140"
            ></el-table-column>
            <el-table-column
              label="姓名"
              prop="name"
              width="120"
            ></el-table-column>
            <el-table-column label="地址" prop="address"></el-table-column>
          </el-table>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

const item = {
  date: '2016-05-02',
  name: '王小虎',
  address: '上海市普陀区金沙江路 1518 弄',
};

@Component
export default class ContainerDemo extends Vue {
  public tableData: object[] = Array(20).fill(item);
}
</script>

<style lang="scss" scoped>
.el-container {
  margin-bottom: 40px;

  &:nth-child(5) .el-aside,
  &:nth-child(6) .el-aside {
    line-height: 260px;
  }

  &:nth-child(7) .el-aside {
    line-height: 320px;
  }

  .el-header,
  .el-footer {
    background-color: #b3c0d1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: #d3dce6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    background-color: #e9eef3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }
}
</style>
