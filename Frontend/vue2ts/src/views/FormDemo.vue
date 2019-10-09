<template>
  <div id="form-demo">
    <el-row align="center" justify="center" style="height: 100px;">
      <el-col :offset="1" :span="22">
        <el-row>
          <el-col :span="2">
            <router-link to="/form-demo/radio-demo">Radio Demo</router-link>
          </el-col>
          <el-col :span="2">
            <router-link to="/form-demo/checkbox-demo"
              >Checkbox Demo</router-link
            >
          </el-col>
          <el-col :span="2">
            <router-link to="/form-demo/input-demo">Input Demo</router-link>
          </el-col>
          <el-col :span="2">
            <router-link to="/form-demo/inputnumber-demo"
              >InputNumber Demo</router-link
            >
          </el-col>
          <el-col :span="2">
            <router-link to="/form-demo/select-demo">Select Demo</router-link>
          </el-col>
          <el-col :span="2">
            <router-link to="/form-demo/cascader-demo"
              >Cascader Demo</router-link
            >
          </el-col>
          <el-col :span="2">
            <router-link to="/form-demo/switch-demo">Switch Demo</router-link>
          </el-col>
          <el-col :span="2">
            <router-link to="/form-demo/slider-demo">Slider Demo</router-link>
          </el-col>
          <el-col :span="2">
            <router-link to="/form-demo/timepicker-demo"
              >TimePicker Demo</router-link
            >
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <router-view />
  </div>
</template>

</script>
<script lang="ts">
import {Component, Vue} from 'vue-property-decorator';

@Component
export default class FormDemo extends Vue {}
</script>

<style lang="scss" scoped>
.el-row {
  margin-top: 8px;
}

.el-col {
  border: 1px #000;
}

.el-input .el-select {
  width: 130px;
}

.input-with-select {
  background-color: #fff;
}

.my-autocomplete {
  li {
    line-height: normal;
    padding: 7px;

    name {
      text-overflow: ellipsis;
      overflow: hidden;
    }

    .addr {
      font-size: 12px;
      color: #b4b4b4;
    }

    .highlighted .addr {
      color: #ddd;
    }
  }
}
</style>
