import { IUser } from 'app/core/user/user.model';

export interface IGrupo {
  id?: number;
  nombre?: string;
  users?: IUser[];
}

export class Grupo implements IGrupo {
  constructor(public id?: number, public nombre?: string, public users?: IUser[]) {}
}
