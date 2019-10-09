<template>
  <div class="inputnumber-demo">
    <el-row align="bottom" justify="space-around" type="flex">
      <el-col :span="3">
        <el-input-number :max="10" :min="1" @change="handleChange" label="描述文字" v-model="num1"></el-input-number>
      </el-col>
      <el-col :span="3">
        <el-input-number :disabled="true" v-model="num2"></el-input-number>
      </el-col>
      <el-col :span="3">
        <el-input-number :min="0" :step="2" v-model="num3"></el-input-number>
      </el-col>
      <el-col :span="3">
        <el-input-number :max="10" :precision="2" :step="0.1" v-model="num4"></el-input-number>
      </el-col>
      <el-col :span="3">
        <el-input-number v-model="num5"></el-input-number>
      </el-col>
      <el-col :span="3">
        <el-input-number size="medium" v-model="num6"></el-input-number>
      </el-col>
      <el-col :span="3">
        <el-input-number size="small" v-model="num7"></el-input-number>
      </el-col>
      <el-col :span="3">
        <el-input-number size="mini" v-model="num8"></el-input-number>
      </el-col>
    </el-row>
    <el-row align="bottom" justify="space-around" type="flex">
      <el-col :span="3">
        <el-input-number
          :max="10"
          :min="1"
          @change="handleChange"
          controls-position="right"
          v-model="num9"
        ></el-input-number>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts">
import { Component, Emit, Vue } from 'vue-property-decorator';

@Component
export default class InputNumberDemo extends Vue {
  public timeout: null = null;
  public num1: number = 1;
  public num2: number = 1;
  public num3: number = 5;
  public num4: number = 1;
  public num5: number = 1;
  public num6: number = 1;
  public num7: number = 1;
  public num8: number = 1;
  public num9: number = 1;
  @Emit('handleChange')
  public handleChange(val: string) {
    console.log(val);
  }
}
</script>