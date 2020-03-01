import { IEstadisticas } from 'app/shared/model/estadisticas.model';
import { IPuntuacionPrueba } from 'app/shared/model/puntuacion-prueba.model';

export interface IZScore {
  id?: number;
  nombre?: string;
  estadisticas?: IEstadisticas[];
  puntuacionPruebas?: IPuntuacionPrueba[];
}

export class ZScore implements IZScore {
  constructor(
    public id?: number,
    public nombre?: string,
    public estadisticas?: IEstadisticas[],
    public puntuacionPruebas?: IPuntuacionPrueba[]
  ) {}
}
