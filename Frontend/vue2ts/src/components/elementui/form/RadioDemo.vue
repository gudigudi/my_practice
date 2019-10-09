<template>
  <div class="radio-demo">
    <el-row align="middle" justify="space-around" type="flex">
      <el-col :span="6">
        <el-radio label="1" v-model="radio">备选项</el-radio>
        <el-radio label="2" v-model="radio">备选项</el-radio>
      </el-col>
      <el-col :span="6">
        <el-radio disabled label="禁用" v-model="radio1">备选项</el-radio>
        <el-radio disabled label="选中且禁用" v-model="radio1">备选项</el-radio>
      </el-col>
      <el-col :span="6">
        <el-radio-group v-model="radio2">
          <el-radio :label="3">备选项</el-radio>
          <el-radio :label="6">备选项</el-radio>
          <el-radio :label="9">备选项</el-radio>
        </el-radio-group>
      </el-col>
      <el-col :span="6">
        <el-radio-group v-model="radio3">
          <el-radio-button label="上海"></el-radio-button>
          <el-radio-button label="北京"></el-radio-button>
          <el-radio-button label="广州"></el-radio-button>
          <el-radio-button label="深圳"></el-radio-button>
        </el-radio-group>
      </el-col>
    </el-row>
    <el-row align="middle" justify="space-around" type="flex">
      <el-col :span="6">
        <el-radio-group size="medium" v-model="radio4">
          <el-radio-button label="上海"></el-radio-button>
          <el-radio-button label="北京"></el-radio-button>
          <el-radio-button label="广州"></el-radio-button>
          <el-radio-button label="深圳"></el-radio-button>
        </el-radio-group>
      </el-col>
      <el-col :span="6">
        <el-radio-group size="small" v-model="radio5">
          <el-radio-button label="上海"></el-radio-button>
          <el-radio-button disabled label="北京"></el-radio-button>
          <el-radio-button label="广州"></el-radio-button>
          <el-radio-button label="深圳"></el-radio-button>
        </el-radio-group>
      </el-col>
      <el-col :span="6">
        <el-radio-group disabled size="mini" v-model="radio6">
          <el-radio-button label="上海"></el-radio-button>
          <el-radio-button label="北京"></el-radio-button>
          <el-radio-button label="广州"></el-radio-button>
          <el-radio-button label="深圳"></el-radio-button>
        </el-radio-group>
      </el-col>
      <el-col :span="6">
        <el-radio border label="1" v-model="radio7">备选项1</el-radio>
        <el-radio border label="2" v-model="radio7">备选项2</el-radio>
      </el-col>
    </el-row>
    <el-row align="middle" justify="space-around" type="flex">
      <el-col :span="6">
        <el-radio border label="1" size="medium" v-model="radio8">备选项1</el-radio>
        <el-radio border label="2" size="medium" v-model="radio8">备选项2</el-radio>
      </el-col>
      <el-col :span="6">
        <el-radio-group size="small" v-model="radio9">
          <el-radio border label="1">备选项1</el-radio>
          <el-radio border disabled label="2">备选项2</el-radio>
        </el-radio-group>
      </el-col>
      <el-col :span="6">
        <el-radio-group disabled size="mini" v-model="radio10">
          <el-radio border label="1">备选项1</el-radio>
          <el-radio border label="2">备选项2</el-radio>
        </el-radio-group>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

@Component
export default class RadioDemo extends Vue {
  public radio: string = '1';
  public radio1: string = '选中且禁用';
  public radio2: number = 3;
  public radio3: string = '上海';
  public radio4: string = '上海';
  public radio5: string = '上海';
  public radio6: string = '上海';
  public radio7: string = '1';
  public radio8: string = '2';
  public radio9: string = '1';
  public radio10: string = '2';
}
</script>