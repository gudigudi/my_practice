<template>
  <div class="timepicker-demo">
    <el-row>
      <el-col :span="3">
        <el-time-select
          :picker-options="{ start: '08:30', step: '00:15', end: '18:30' }"
          placeholder="选择时间"
          v-model="value1"
        ></el-time-select>
      </el-col>
      <el-col :span="3">
        <el-time-picker
          :picker-options="{ selectableRange: '18:30:00 - 20:30:00' }"
          placeholder="任意时间点"
          v-model="value2"
        ></el-time-picker>
        <el-time-picker
          :picker-options="{ selectableRange: '18:30:00 - 20:30:00' }"
          arrow-control
          placeholder="任意时间点"
          v-model="value3"
        ></el-time-picker>
      </el-col>
      <el-col :span="3">
        <el-time-select
          :picker-options="{ start: '08:30', step: '00:15', end: '18:30' }"
          placeholder="起始时间"
          v-model="startTime"
        ></el-time-select>
        <el-time-select
          :picker-options="{
            start: '08:30',
            step: '00:15',
            end: '18:30',
            minTime: startTime
          }"
          placeholder="结束时间"
          v-model="endTime"
        ></el-time-select>
      </el-col>
      <el-col :span="6">
        <el-time-picker
          end-placeholder="结束时间"
          is-range
          placeholder="选择时间范围"
          range-separator="至"
          start-placeholder="开始时间"
          v-model="value4"
        ></el-time-picker>
        <el-time-picker
          arrow-control
          end-placeholder="结束时间"
          is-range
          placeholder="选择时间范围"
          range-separator="至"
          start-placeholder="开始时间"
          v-model="value5"
        ></el-time-picker>
      </el-col>
      <el-col :span="3"></el-col>
      <el-col :span="3"></el-col>
      <el-col :span="3"></el-col>
    </el-row>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

@Component
export default class TimePickerDemo extends Vue {
  public value1: string = '';
  public value2: Date = new Date(2016, 9, 10, 18, 40);
  public value3: Date = new Date(2016, 9, 10, 18, 40);
  public startTime: string = '';
  public endTime: string = '';
  public value4: Date[] = [new Date(2016, 9, 10, 8, 40), new Date(2016, 9, 10, 9, 40)];
  public value5: Date[] = [new Date(2016, 9, 10, 8, 40), new Date(2016, 9, 10, 9, 40)];
}
</script>

<style scoped></style>
