import { Moment } from 'moment';
import { IResultadoPrueba } from 'app/shared/model/resultado-prueba.model';
import { SospechaClinica } from 'app/shared/model/enumerations/sospecha-clinica.model';
import { SospechaClinicaSugerida } from 'app/shared/model/enumerations/sospecha-clinica-sugerida.model';

export interface IInforme {
  id?: number;
  sospechaClinica?: SospechaClinica;
  sospechaClinicaSugerida?: SospechaClinicaSugerida;
  fechaEvaluacion?: Moment;
  motivoConsulta?: string;
  orientacion?: string;
  memoria?: string;
  praxias?: string;
  lenguaje?: string;
  funcionesEjecutivas?: string;
  conducta?: string;
  actividadesDiarias?: string;
  resumen?: string;
  resultadoPruebas?: IResultadoPrueba[];
  userEmail?: string;
  userId?: number;
  pacienteNombre?: string;
  pacienteId?: number;
}

export class Informe implements IInforme {
  constructor(
    public id?: number,
    public sospechaClinica?: SospechaClinica,
    public sospechaClinicaSugerida?: SospechaClinicaSugerida,
    public fechaEvaluacion?: Moment,
    public motivoConsulta?: string,
    public orientacion?: string,
    public memoria?: string,
    public praxias?: string,
    public lenguaje?: string,
    public funcionesEjecutivas?: string,
    public conducta?: string,
    public actividadesDiarias?: string,
    public resumen?: string,
    public resultadoPruebas?: IResultadoPrueba[],
    public userEmail?: string,
    public userId?: number,
    public pacienteNombre?: string,
    public pacienteId?: number
  ) {}
}
