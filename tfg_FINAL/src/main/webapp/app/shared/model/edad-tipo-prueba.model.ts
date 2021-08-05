import { TipoPrueba } from 'app/shared/model/enumerations/tipo-prueba.model';

export interface IEdadTipoPrueba {
  id?: number;
  codigo?: string;
  edadMinima?: number;
  edadMaxima?: number;
  tipoPrueba?: TipoPrueba;
}

export class EdadTipoPrueba implements IEdadTipoPrueba {
  constructor(
    public id?: number,
    public codigo?: string,
    public edadMinima?: number,
    public edadMaxima?: number,
    public tipoPrueba?: TipoPrueba,
  ) {}
}
