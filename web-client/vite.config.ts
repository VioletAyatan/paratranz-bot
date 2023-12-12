import {defineConfig} from 'vite';
import vue from '@vitejs/plugin-vue';
import Pages from 'vite-plugin-pages'; //See: https://github.com/hannoeru/vite-plugin-pages
import AutoImport from 'unplugin-auto-import/vite'; //See: https://github.com/unplugin/unplugin-auto-import
import Components from 'unplugin-vue-components/vite'; //See: https://github.com/unplugin/unplugin-vue-components
import ElementPlus from 'unplugin-element-plus/vite'; //See: https://element-plus.org/zh-CN/guide/quickstart.htm
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers';

// https://vitejs.dev/config/
export default defineConfig({
    server: {
        open: true
    },
    plugins: [
        vue(),
        AutoImport({
            imports: ['vue', 'vue-router', 'pinia'],
            dts: true,
            resolvers: [ElementPlusResolver()],
        }),
        Components({
            dts: true,
            resolvers: [ElementPlusResolver()],
        }),
        ElementPlus({}),
        Pages(),
    ],
});
