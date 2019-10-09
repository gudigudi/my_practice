<template>
  <div id="button-demo">
    <el-row>
      <el-button autofocus>默认按钮</el-button>
      <el-button>默认按钮</el-button>
      <el-button type="primary">主要按钮</el-button>
      <el-button type="success">成功按钮</el-button>
      <el-button type="info">信息按钮</el-button>
      <el-button type="warning">警告按钮</el-button>
      <el-button type="danger">危险按钮</el-button>
    </el-row>
    <el-row>
      <el-button plain>朴素按钮</el-button>
      <el-button plain type="primary">主要按钮</el-button>
      <el-button plain type="success">成功按钮</el-button>
      <el-button plain type="info">信息按钮</el-button>
      <el-button plain type="warning">警告按钮</el-button>
      <el-button plain type="danger">危险按钮</el-button>
    </el-row>
    <el-row>
      <el-button
        v-for="(value, key, index) in {
          '': '圆角按钮',
          primary: '主要按钮',
          success: '成功按钮',
          info: '信息按钮',
          warning: '警告按钮',
          danger: '危险按钮'
        }"
        :key="index"
        :type="key"
        round
        >{{ value }}</el-button
      >
    </el-row>
    <el-row>
      <el-button
        v-for="(value, key, index) in {
          search: '',
          edit: 'primary',
          check: 'success',
          message: 'info',
          'star-off': 'warning',
          delete: 'danger'
        }"
        :key="index"
        circle
        :icon="'el-icon-' + key"
        :type="value"
      ></el-button>
    </el-row>
    <el-row>
      <el-button
        disabled
        v-for="(value, key, index) in {
          '': '默认按钮',
          primary: '主要按钮',
          success: '成功按钮',
          info: '信息按钮',
          warning: '警告按钮',
          danger: '危险按钮'
        }"
        :key="index"
        :type="key"
        >{{ value }}</el-button
      >
    </el-row>
    <el-row>
      <el-button
        disabled
        plain
        v-for="(value, key, index) in {
          '': '朴素按钮',
          primary: '主要按钮',
          success: '成功按钮',
          info: '信息按钮',
          warning: '警告按钮',
          danger: '危险按钮'
        }"
        :key="index"
        :type="key"
        >{{ value }}</el-button
      >
    </el-row>
    <el-row>
      <el-button
        v-for="(item, index) in ['文字按钮', '文字按钮']"
        :key="index"
        :disabled="index == 0 ? false : true"
        type="text"
        >{{ item }}</el-button
      >
    </el-row>
    <el-row>
      <el-button icon="el-icon-edit" type="primary"></el-button>
      <el-button icon="el-icon-share" type="primary"></el-button>
      <el-button icon="el-icon-delete" type="primary"></el-button>
      <el-button icon="el-icon-search" type="primary">搜索</el-button>
      <el-button type="primary">
        上传
        <i class="el-icon-upload el-icon--right"></i>
      </el-button>
    </el-row>
    <el-row>
      <el-button-group>
        <el-button type="primary" icon="el-icon-arrow-left">上一页</el-button>
        <el-button type="primary">
          下一页
          <i class="el-icon-arrow-right el-icon--right"></i>
        </el-button>
      </el-button-group>
      <el-button-group>
        <el-button
          v-for="(item, index) in ['edit', 'share', 'delete']"
          :key="index"
          :icon="'el-icon-' + item"
          type="primary"
        ></el-button>
      </el-button-group>
    </el-row>
    <el-row>
      <el-button :loading="true" type="primary">加载中</el-button>
    </el-row>
    <el-row>
      <el-button
        v-for="(value, key, index) in {
          '': '默认按钮',
          medium: '中等按钮',
          small: '小型按钮',
          mini: '超小按钮'
        }"
        :key="index"
        :size="key"
        >{{ value }}</el-button
      >
    </el-row>
    <el-row>
      <el-button
        round
        v-for="(value, key, index) in {
          '': '默认按钮',
          medium: '中等按钮',
          small: '小型按钮',
          mini: '超小按钮'
        }"
        :key="index"
        :size="key"
        >{{ value }}</el-button
      >
    </el-row>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

@Component
export default class ButtonDemo extends Vue {}
</script>

<style scoped>
.el-button {
  margin-bottom: 20px;
}
</style>
