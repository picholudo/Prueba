import { Moment } from 'moment';

export interface IPuntosCorte {
  id?: number;
  nombre?: string;
  limite?: Moment;
  superarlo?: boolean;
}

export class PuntosCorte implements IPuntosCorte {
  constructor(public id?: number, public nombre?: string, public limite?: Moment, public superarlo?: boolean) {
    this.superarlo = this.superarlo || false;
  }
}
