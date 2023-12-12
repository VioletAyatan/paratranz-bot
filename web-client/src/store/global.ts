import {defineStore} from "pinia";

const useGlobalStore = defineStore('global', {
    state: () => {
        return {
            topic: '',
            payload: ''
        }
    },
    actions: {
        /**
         * 清空payload
         */
        cleanPayload() {
            this.payload = ''
        },
    }
})

export {useGlobalStore}