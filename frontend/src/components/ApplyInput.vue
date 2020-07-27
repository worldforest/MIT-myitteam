<template>
	<div>
		<h3 class="ml-4">모집 인원 : </h3>
		<v-row>
			<v-col class="d-flex mx-auto" cols="5">
				<v-select
				:items="selectArea"
				label="분야"
				outlined
				v-model="applyData.part"
				></v-select>
			</v-col>

			<v-col class="d-flex mx-auto" cols="5">
				<v-select
					:items="selectPeople"
					label="명 수"
					outlined
					v-model="applyData.headcount"
				></v-select>
			</v-col>
		</v-row>

		<v-col cols="12" md="11" class="mx-auto">
		<v-textarea
			outlined
			name="input-7-4"
			label="담당 업무"
			v-model="applyData.task"
		></v-textarea>
		</v-col>

		<v-col cols="12" md="11" class="mx-auto">
		<v-textarea
			outlined
			name="input-7-4"
			label="필수 역량"
			class="mx-auto"
			v-model="applyData.ability"
		></v-textarea>
		</v-col>

		<v-col cols="12" md="11" class="mx-auto">
		<v-textarea
			outlined
			name="input-7-4"
			label="우대 사항"
			v-model="applyData.advantage"
		></v-textarea>
		</v-col>

		<v-btn @click="setApplyData">추가하기</v-btn>
	</div>
</template>

<script>
export default {
	name: 'ApplyInput',
	data() {
		return{

		}
	}
}
</script>

<style>

</style>