import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEvaluacion } from 'app/shared/model/evaluacion.model';

type EntityResponseType = HttpResponse<IEvaluacion>;
type EntityArrayResponseType = HttpResponse<IEvaluacion[]>;

@Injectable({ providedIn: 'root' })
export class EvaluacionService {
  public resourceUrl = SERVER_API_URL + 'api/evaluacions';

  constructor(protected http: HttpClient) {}

  create(evaluacion: IEvaluacion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(evaluacion);
    return this.http
      .post<IEvaluacion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(evaluacion: IEvaluacion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(evaluacion);
    return this.http
      .put<IEvaluacion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEvaluacion>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEvaluacion[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(evaluacion: IEvaluacion): IEvaluacion {
    const copy: IEvaluacion = Object.assign({}, evaluacion, {
      fecha: evaluacion.fecha && evaluacion.fecha.isValid() ? evaluacion.fecha.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fecha = res.body.fecha ? moment(res.body.fecha) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((evaluacion: IEvaluacion) => {
        evaluacion.fecha = evaluacion.fecha ? moment(evaluacion.fecha) : undefined;
      });
    }
    return res;
  }
}
