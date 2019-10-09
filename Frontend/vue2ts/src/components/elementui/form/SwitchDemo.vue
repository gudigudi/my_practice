<template>
  <div class="switch-demo">
    <el-row>
      <el-col :span="3">
        <el-switch active-color="#13ce66" inactive-color="#ff4949" v-model="value1"></el-switch>
      </el-col>
      <el-col :span="3">
        <el-switch active-text="按月付费" inactive-text="按年付费" v-model="value2"></el-switch>
      </el-col>
      <el-col :span="3">
        <el-tooltip :content="'Switch value: ' + value3" placement="top">
          <el-switch
            active-color="#13ce66"
            active-value="100"
            inactive-color="#ff4949"
            inactive-value="0"
            v-model="value3"
          ></el-switch>
        </el-tooltip>
      </el-col>
      <el-col :span="3">
        <el-switch disabled v-model="value4"></el-switch>
        <el-switch disabled v-model="value5"></el-switch>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component
export default class SwitchDemo extends Vue {
  public value1: boolean = true;
  public value2: boolean = true;
  public value3: string = "100";
  public value4: boolean = true;
  public value5: boolean = false;
}
</script>
