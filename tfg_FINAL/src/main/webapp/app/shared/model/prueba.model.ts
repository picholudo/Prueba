import { TipoPrueba } from 'app/shared/model/enumerations/tipo-prueba.model';

export interface IPrueba {
  id?: number;
  tipoPrueba?: TipoPrueba;
  nombre?: string;
  codigo?: string;
}

export class Prueba implements IPrueba {
  constructor(public id?: number, public tipoPrueba?: TipoPrueba, public nombre?: string, public codigo?: string) {}
}
