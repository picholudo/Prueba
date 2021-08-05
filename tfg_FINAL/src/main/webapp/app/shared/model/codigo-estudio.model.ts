import { NivelEstudios } from 'app/shared/model/enumerations/nivel-estudios.model';

export interface ICodigoEstudio {
  id?: number;
  nivelEstudios?: NivelEstudios;
  codigo?: string;
}

export class CodigoEstudio implements ICodigoEstudio {
  constructor(public id?: number, public nivelEstudios?: NivelEstudios, public codigo?: string) {}
}
