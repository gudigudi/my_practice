import Vue from 'vue';
import Vuex from 'vuex';
import IAppState from './modules/app';
import { IUserState } from './modules/user';

Vue.use(Vuex);

interface IRootState {
  app: IAppState,
  user: IUserState,
}

const store = new Vuex.Store<IRootState>({});

export default store;