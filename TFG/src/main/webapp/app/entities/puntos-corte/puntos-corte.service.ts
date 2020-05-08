import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPuntosCorte } from 'app/shared/model/puntos-corte.model';

type EntityResponseType = HttpResponse<IPuntosCorte>;
type EntityArrayResponseType = HttpResponse<IPuntosCorte[]>;

@Injectable({ providedIn: 'root' })
export class PuntosCorteService {
  public resourceUrl = SERVER_API_URL + 'api/puntos-cortes';

  constructor(protected http: HttpClient) {}

  create(puntosCorte: IPuntosCorte): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(puntosCorte);
    return this.http
      .post<IPuntosCorte>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(puntosCorte: IPuntosCorte): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(puntosCorte);
    return this.http
      .put<IPuntosCorte>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPuntosCorte>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPuntosCorte[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  deleteAll(): Observable<HttpResponse<{}>> {
    console.error("Hola");
    return this.http.delete(`${this.resourceUrl}`, { observe: 'response' });
  }

  protected convertDateFromClient(puntosCorte: IPuntosCorte): IPuntosCorte {
    const copy: IPuntosCorte = Object.assign({}, puntosCorte, {
      limite: puntosCorte.limite && puntosCorte.limite.isValid() ? puntosCorte.limite.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.limite = res.body.limite ? moment(res.body.limite) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((puntosCorte: IPuntosCorte) => {
        puntosCorte.limite = puntosCorte.limite ? moment(puntosCorte.limite) : undefined;
      });
    }
    return res;
  }
}
