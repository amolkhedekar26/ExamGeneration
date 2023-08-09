<template>
  <div>
    <v-select
      placeholder="Type here to search ..."
      v-model="selected"
      :options="dataList"
    />
  </div>
</template>

<script>
import vSelect from "vue-select";
import "vue-select/dist/vue-select.css";

export default {
  name: "AutoCompleteInput",
  components: {
    vSelect,
  },
  data: () => {
    return {
      selected: "",
    };
  },
  props:{
      dataList:Array
  }
};
</script>

<style scoped>
</style>