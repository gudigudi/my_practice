<template>
  <div class="select-demo">
    <el-row>
      <el-col :span="3">
        <el-select placeholder="请选择" v-model="value">
          <el-option
            :key="item.value"
            :label="item.label"
            :value="item.value"
            v-for="item in options"
          ></el-option>
        </el-select>
      </el-col>
      <el-col :span="3">
        <el-select placeholder="请选择" v-model="value2">
          <el-option
            :disabled="item.disabled"
            :key="item.value"
            :label="item.label"
            :value="item.value"
            v-for="item in options"
          ></el-option>
        </el-select>
      </el-col>
      <el-col :span="3">
        <el-select disabled placeholder="请选择" v-model="value3">
          <el-option
            :key="item.value"
            :label="item.label"
            :value="item.value"
            v-for="item in options"
          ></el-option>
        </el-select>
      </el-col>
      <el-col :span="3">
        <el-select clearable placeholder="请选择" v-model="value4">
          <el-option
            :key="item.value"
            :label="item.label"
            :value="item.value"
            v-for="item in options"
          ></el-option>
        </el-select>
      </el-col>
      <el-col :span="3">
        <el-select multiple placeholder="请选择" v-model="value5">
          <el-option
            :key="item.value"
            :label="item.label"
            :value="item.value"
            v-for="item in options"
          ></el-option>
        </el-select>
      </el-col>
      <el-col :span="3">
        <el-select collapse-tags multiple placeholder="请选择" v-model="value6">
          <el-option
            :key="item.value"
            :label="item.label"
            :value="item.value"
            v-for="item in options"
          ></el-option>
        </el-select>
      </el-col>
      <el-col :span="3">
        <el-select placeholder="请选择" v-model="value7">
          <el-option
            :key="item.value"
            :label="item.label"
            :value="item.value"
            v-for="item in cities2"
          >
            <span style="float:left;">{{ item.label }}</span>
            <span style="float:right;color:#8492a6;font-sizse:13px;">{{ item.value }}</span>
          </el-option>
        </el-select>
      </el-col>
    </el-row>
    <el-row align="bottom" justify="space-around" type="flex">
      <el-col :span="3">
        <el-select placeholder="请选择" v-model="value8">
          <el-option-group :key="group.label" :label="group.label" v-for="group in options3">
            <el-option
              :key="item.label"
              :label="item.label"
              :value="item.value"
              v-for="item in group.options"
            ></el-option>
          </el-option-group>
        </el-select>
      </el-col>
      <el-col :span="3">
        <el-select filterable placeholder="请选择" v-model="value9">
          <el-option
            :key="item.value"
            :label="item.label"
            :value="item.value"
            v-for="item in options"
          ></el-option>
        </el-select>
      </el-col>
      <el-col :span="3">
        <el-select
          :loading="loading"
          :remote-method="remoteMethod"
          filterable
          multiple
          placeholder="请输入关键词"
          remote
          reserve-keyword
          v-model="value10"
        >
          <el-option
            :key="item.value"
            :label="item.label"
            :value="item.value"
            v-for="item in options4"
          ></el-option>
        </el-select>
      </el-col>
      <el-col :span="3">
        <el-select
          allow-create
          default-first-option
          filterable
          multiple
          placeholder="请选择文章标签"
          v-model="value11"
        >
          <el-option
            :key="item.value"
            :label="item.label"
            :value="item.value"
            v-for="item in options5"
          ></el-option>
        </el-select>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts">
import { Component, Emit, Vue } from 'vue-property-decorator';

interface Option {
  value: string;
  label: string;
  disabled?: boolean;
}

@Component
export default class SelectDemo extends Vue {
  public options: Option[] = [
    { value: '选项1', label: '黄金糕' },
    { value: '选项2', label: '双皮奶', disabled: true },
    { value: '选项3', label: '蚵仔煎' },
    { value: '选项4', label: '龙须面' },
    { value: '选项5', label: '北京烤鸭' },
  ];
  public value: string = '';
  public value2: string = '';
  public value3: string = '';
  public value4: string = '';
  public value5: string = '';
  public value6: string = '';
  public value7: string = '';
  public cities2: Option[] = [
    {
      value: 'Beijing',
      label: '北京',
    },
    {
      value: 'Shanghai',
      label: '上海',
    },
    {
      value: 'Nanjing',
      label: '南京',
    },
    {
      value: 'Chengdu',
      label: '成都',
    },
    {
      value: 'Shenzhen',
      label: '深圳',
    },
    {
      value: 'Guangzhou',
      label: '广州',
    },
  ];
  public options3: Array<{ label: string; options: Option[] }> = [
    {
      label: '热门城市',
      options: [
        {
          value: 'Shanghai',
          label: '上海',
        },
        {
          value: 'Beijing',
          label: '北京',
        },
      ],
    },
    {
      label: '城市名',
      options: [
        {
          value: 'Chengdu',
          label: '成都',
        },
        {
          value: 'Shenzhen',
          label: '深圳',
        },
        {
          value: 'Guangzhou',
          label: '广州',
        },
        {
          value: 'Dalian',
          label: '大连',
        },
      ],
    },
  ];
  public value8: string = '';
  public value9: string = '';
  public value10: string = '';
  public options4: Option[] = [];
  public loading: boolean = false;
  public value11: [] = [];
  public options5: Option[] = [
    { value: 'HTML', label: 'HTML' },
    { value: 'CSS', label: 'CSS' },
    { value: 'JavaScript', label: 'JavaScript' },
  ];
  public list: Option[] = [];

  @Emit('remoteMethod')
  public remoteMethod(query: any) {
    if (query !== '') {
      this.loading = true;
      setTimeout(() => {
        this.loading = false;
        this.options4 = this.list.filter((item) => {
          return item.label.toLowerCase().indexOf(query.toLowerCase()) > -1;
        });
      }, 200);
    } else {
      this.options4 = [];
    }
  }
}
</script>