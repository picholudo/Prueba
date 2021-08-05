import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, observeOn } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInforme } from 'app/shared/model/informe.model';

type EntityResponseType = HttpResponse<IInforme>;
type EntityArrayResponseType = HttpResponse<IInforme[]>;

@Injectable({ providedIn: 'root' })
export class InformeService {
  public resourceUrl = SERVER_API_URL + 'api/informes';
  public resourceUrl_2 = SERVER_API_URL + 'api/sospechaClinicaSugerida';

  constructor(protected http: HttpClient) {}

  create(informe: IInforme): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(informe);
    return this.http
      .post<IInforme>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(informe: IInforme): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(informe);
    return this.http
      .put<IInforme>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IInforme>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IInforme[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getDiagnosis() : Observable<any>{
    //const headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');
    return this.http
    .get(`${this.resourceUrl_2}`, {responseType:"text"})
  }


  protected convertDateFromClient(informe: IInforme): IInforme {
    const copy: IInforme = Object.assign({}, informe, {
      fechaEvaluacion:
        informe.fechaEvaluacion && informe.fechaEvaluacion.isValid() ? informe.fechaEvaluacion.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fechaEvaluacion = res.body.fechaEvaluacion ? moment(res.body.fechaEvaluacion) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((informe: IInforme) => {
        informe.fechaEvaluacion = informe.fechaEvaluacion ? moment(informe.fechaEvaluacion) : undefined;
      });
    }
    return res;
  }
}
