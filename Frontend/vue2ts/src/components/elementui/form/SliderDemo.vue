<template>
  <div class="slider-demo">
    <el-row>
      <el-col :span="4">
        <div class="block">
          <span class="demonstration">默认</span>
          <el-slider v-model="value1"></el-slider>
        </div>
        <div class="block">
          <span class="demonstration">自定义初始值</span>
          <el-slider v-model="value2"></el-slider>
        </div>
        <div class="block">
          <span class="demonstration">隐藏Tooltip</span>
          <el-slider :show-tooltip="false" v-model="value3"></el-slider>
        </div>
        <div class="block">
          <span class="demonstration">格式化Tooltip</span>
          <el-slider
            :format-tooltip="formatTooltip"
            v-model="value4"
          ></el-slider>
        </div>
      </el-col>
      <el-col :span="4">
        <div class="block">
          <span class="demonstration">禁用</span>
          <el-slider disabled v-model="value5"></el-slider>
        </div>
        <div class="block">
          <span class="demonstration">不显示间断点</span>
          <el-slider :step="10" v-model="value6"></el-slider>
        </div>
        <div class="block">
          <span class="demonstration">显示间断点</span>
          <el-slider :step="10" show-stops v-model="value7"></el-slider>
        </div>
        <div class="block">
          <el-slider show-input v-model="value8"></el-slider>
        </div>
        <div class="block">
          <el-slider :max="10" range show-stops v-model="value9"></el-slider>
        </div>
      </el-col>
      <el-col :span="4">
        <div class="block">
          <el-slider height="200px" v-model="value10" vertical></el-slider>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts">
import {Component, Emit, Vue} from 'vue-property-decorator';

@Component
export default class SliderDemo extends Vue {
  public value1: number = 0;
  public value2: number = 50;
  public value3: number = 36;
  public value4: number = 48;
  public value5: number = 42;
  public value6: number = 0;
  public value7: number = 0;
  public value8: number = 0;
  public value9: number[] = [4, 8];
  public value10: number = 0;

  @Emit('formatTooltip')
  public formatTooltip(val: number) {
    return val / 100;
  }
}
</script>


<style scoped>
.el-col {
  margin-left: 24px;
  margin-right: 24px;
}
</style>
