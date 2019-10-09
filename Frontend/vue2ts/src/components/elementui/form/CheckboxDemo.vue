<template>
  <div class="checkbox-demo">
    <el-row>
      <el-col :span="6">
        <el-checkbox v-model="checked">备选项</el-checkbox>
      </el-col>
    </el-row>
    <el-row align="middle" justify="space-around" type="flex">
      <el-col :span="8">
        <el-checkbox disabled v-model="checked1">备选项1</el-checkbox>
        <el-checkbox disabled v-model="checked2">备选项2</el-checkbox>
      </el-col>
      <el-col :span="8">
        <el-checkbox-group v-model="checkList">
          <el-checkbox label="复选框 A"></el-checkbox>
          <el-checkbox label="复选框 B"></el-checkbox>
          <el-checkbox label="复选框 C"></el-checkbox>
          <el-checkbox disabled label="禁用"></el-checkbox>
          <el-checkbox disabled label="选中且禁用"></el-checkbox>
        </el-checkbox-group>
      </el-col>
      <el-col :span="8">
        <el-checkbox
          :indeterminate="isIndeterminate"
          @change="handleCheckAllChange"
          v-model="checkAll"
        >全选</el-checkbox>
        <el-checkbox-group @change="handleCheckedCitiesChange" v-model="checkedCities">
          <el-checkbox :key="city" :label="city" v-for="city in cities">{{ city }}</el-checkbox>
        </el-checkbox-group>
      </el-col>
    </el-row>
    <el-row align="middle" justify="space-around" type="flex">
      <el-col :span="6">
        <el-checkbox-group :max="2" :min="1" v-model="checkedCities1">
          <el-checkbox :key="city" :label="city" v-for="city in cities">{{ city }}</el-checkbox>
        </el-checkbox-group>
      </el-col>
      <el-col :span="6">
        <el-checkbox-group v-model="checkboxGroup1">
          <el-checkbox-button :key="city" :label="city" v-for="city in cities">{{ city }}</el-checkbox-button>
        </el-checkbox-group>
      </el-col>
      <el-col :span="6">
        <el-checkbox-group size="medium" v-model="checkboxGroup2">
          <el-checkbox-button :key="city" :label="city" v-for="city in cities">{{ city }}</el-checkbox-button>
        </el-checkbox-group>
      </el-col>
      <el-col :span="6">
        <el-checkbox-group size="small" v-model="checkboxGroup2">
          <el-checkbox-button :key="city" :label="city" v-for="city in cities">{{ city }}</el-checkbox-button>
        </el-checkbox-group>
      </el-col>
      <el-col :span="6">
        <el-checkbox-group disabled size="mini" v-model="checkboxGroup2">
          <el-checkbox-button :key="city" :label="city" v-for="city in cities">{{ city }}</el-checkbox-button>
        </el-checkbox-group>
      </el-col>
    </el-row>
    <el-row align="middle" justify="space-around" type="flex">
      <el-col :span="6">
        <el-checkbox border label="备选项1" v-model="checked3"></el-checkbox>
        <el-checkbox border label="备选项2" v-model="checked4"></el-checkbox>
      </el-col>
      <el-col :span="6">
        <el-checkbox border label="备选项1" size="medium" v-model="checked5"></el-checkbox>
        <el-checkbox border label="备选项2" size="medium" v-model="checked6"></el-checkbox>
      </el-col>
      <el-col :span="6">
        <el-checkbox-group size="small" v-model="checkboxGroup5">
          <el-checkbox border label="备选项1"></el-checkbox>
          <el-checkbox border disabled label="备选项2"></el-checkbox>
        </el-checkbox-group>
      </el-col>
      <el-col :span="6">
        <el-checkbox-group size="mini" v-model="checkboxGroup6">
          <el-checkbox border disabled label="备选项1"></el-checkbox>
          <el-checkbox border disabled label="备选项2"></el-checkbox>
        </el-checkbox-group>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

const cityOptions = ['上海', '北京', '广州', '深圳'];

@Component
export default class CheckboxDemo extends Vue {
  public checked: boolean = true;
  public checked1: boolean = true;
  public checked2: boolean = true;
  public checkList: string[] = ['选中且禁用', '复选框 A'];
  public checkAll: boolean = false;
  public checkedCities: string[] = ['上海', '北京'];
  public checkedCities1: string[] = ['上海', '北京'];
  public checkboxGroup1: string[] = ['上海'];
  public checkboxGroup2: string[] = ['上海'];
  public checkboxGroup3: string[] = ['上海'];
  public checkboxGroup4: string[] = ['上海'];
  public cities: string[] = cityOptions;
  public isIndeterminate: boolean = true;
  public checked3: boolean = true;
  public checked4: boolean = false;
  public checked5: boolean = true;
  public checked6: boolean = false;
  public checkboxGroup5: [] = [];
  public checkboxGroup6: [] = [];
  public handleCheckAllChange(val: any) {
    this.checkedCities = val ? cityOptions : [];
    this.isIndeterminate = false;
  }
  public handleCheckedCitiesChange(val: any) {
    const checkedCount = val.length;
    this.checkAll = checkedCount === this.cities.length;
    this.isIndeterminate =
      checkedCount > 0 && checkedCount < this.cities.length;
  }
}
</script>