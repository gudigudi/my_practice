<template>
  <div class="tab" :class="stackedClass">
    <ul :class="classList" role="tablist">
      <slot name="tab" v-for="(tab,index) in tabs" :tab="tab" :index="index"
            :click-handler="navigateToTab">
        <li :class="{active:tab.active}" class="tab" :key="tab" role="presentation">
          <span v-if="textPosition==='top'" class="title title-top"
                :style="tab.active ? activeTitleColor : {}">{{tab.title}}</span>
          <a href="`#${tab.id}`"
             @click.prevent="navigateToTab(index)"
             :style="tab.active ? activeTabStyle : {}"
             :aria-selected="tab.active" :aria-controls="`#${tab.id}`"
             role="tab">
            <i :class="tab.icon">
              <span v-if="textPosition==='center'">{{tab.title}}</span>
            </i>
          </a>
          <span v-if="textPosition==='bottom'" class="title title-bottom"
                :style="tab.active?activeTitleColor:{}">
          {{tab.title}}
        </span>
        </li>
      </slot>
    </ul>
    <div class="tab-content">
      <slot></slot>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'tab',
    props: {
      activeTabColor: String,
      activeTextColor: String,
      textPosition: {
        type: String,
        default: 'center'
      },
      type: {
        type: String,
        default: "tab"
      },
      direction: {
        type: String,
        default: 'horizontal'
      },
      centered: Boolean,
      startIndex: {
        type: Number,
        default: 0
      }
    },
    data () {
      return {
        activeTabIndex: 0,
        tabs: []
      }
    }
  }
</script>
