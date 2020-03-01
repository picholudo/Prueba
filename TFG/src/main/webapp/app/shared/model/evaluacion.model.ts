import { Moment } from 'moment';
import { IPuntuacionPrueba } from 'app/shared/model/puntuacion-prueba.model';
import { Sospecha } from 'app/shared/model/enumerations/sospecha.model';

export interface IEvaluacion {
  id?: number;
  fecha?: Moment;
  valoracion?: Sospecha;
  puntuacionPruebas?: IPuntuacionPrueba[];
  pacienteId?: number;
}

export class Evaluacion implements IEvaluacion {
  constructor(
    public id?: number,
    public fecha?: Moment,
    public valoracion?: Sospecha,
    public puntuacionPruebas?: IPuntuacionPrueba[],
    public pacienteId?: number
  ) {}
}
