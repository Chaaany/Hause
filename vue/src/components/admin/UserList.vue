<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>유저 목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="users.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>아이디</b-th>
              <b-th>이름</b-th>
              <b-th>비밀번호</b-th>
              <b-th>이메일</b-th>
              <b-th>가입날짜</b-th>
              <b-th>수정</b-th>
              <b-th>삭제</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <user-list-item
              v-for="user in users"
              :key="user.userid"
              v-bind="user"
            />
          </tbody>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
  </b-container>
</template>

<script>
import { getUserlist } from "@/api/member.js";
import UserListItem from "@/components/admin/item/UserListItem.vue";

export default {
  name: "UserList",
  components: {
    UserListItem,
  },
  data() {
    return {
      users: [],
    };
  },
  created() {
    getUserlist(
      (response) => {
        this.users = response.data;
      },
      (error) => {
        console.log(error);
      } /* eslint-disable */
    );
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
