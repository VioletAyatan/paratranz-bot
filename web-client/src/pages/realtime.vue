<script setup lang="ts">
import {ref} from "vue";

const formData = ref({
  stationNo: '',
  boxNo: ''
})
</script>

<template>
  <div class="page_realtime">
    <el-form label-width="120" label-position="right" inline>
      <el-form-item label="工点编号">
        <el-input v-model="formData.stationNo" placeholder="工点编号"/>
      </el-form-item>
      <el-form-item label="轨旁设备编号">
        <el-input v-model="formData.boxNo" placeholder="轨旁设备编号"/>
      </el-form-item>
    </el-form>
  </div>
</template>

<style lang="scss"></style>
