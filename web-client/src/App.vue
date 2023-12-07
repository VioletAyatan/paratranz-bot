<script setup lang="ts">
</script>

<template>
  <div id="app">
    <div>
      <RouterView/>
    </div>
    <div>
      <el-form label-width="120">
        <el-form-item label="Topic">
          <el-input readonly placeholder="Topic"/>
        </el-form-item>
        <el-form-item label="Payload">
          <el-input type="textarea" readonly placeholder="Payload"/>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<style scoped>
</style>
