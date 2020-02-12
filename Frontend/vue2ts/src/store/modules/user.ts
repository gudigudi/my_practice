import { VuexModule, Module, Action, Mutation, getModule } from 'vuex-module-decorators'

export interface IUserState {
    id_token: string,
}

@Module({ dynamic: true, name: 'user' })
class User extends VuexModule implements IUserState {
    public id_token = '';

    @Mutation
    private SET_TOKEN(token: string) {
        this.id_token = token;
        localStorage.setItem('token', token);
    }

    @Mutation
    private DEL_TOKEN() {
        this.id_token = '';
        localStorage.removeItem('id_token');
    }

    @Action
    public async Login() {
        this.SET_TOKEN(`token!!!`);
    }

    @Action
    public async Logout() {
        this.DEL_TOKEN();
    }
}

export const UserModule = getModule(User)