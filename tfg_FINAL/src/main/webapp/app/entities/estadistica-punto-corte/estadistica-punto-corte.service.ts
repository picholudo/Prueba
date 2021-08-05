import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEstadisticaPuntoCorte } from 'app/shared/model/estadistica-punto-corte.model';

type EntityResponseType = HttpResponse<IEstadisticaPuntoCorte>;
type EntityArrayResponseType = HttpResponse<IEstadisticaPuntoCorte[]>;

@Injectable({ providedIn: 'root' })
export class EstadisticaPuntoCorteService {
  public resourceUrl = SERVER_API_URL + 'api/estadistica-punto-cortes';

  constructor(protected http: HttpClient) {}

  create(estadisticaPuntoCorte: IEstadisticaPuntoCorte): Observable<EntityResponseType> {
    return this.http.post<IEstadisticaPuntoCorte>(this.resourceUrl, estadisticaPuntoCorte, { observe: 'response' });
  }

  update(estadisticaPuntoCorte: IEstadisticaPuntoCorte): Observable<EntityResponseType> {
    return this.http.put<IEstadisticaPuntoCorte>(this.resourceUrl, estadisticaPuntoCorte, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEstadisticaPuntoCorte>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEstadisticaPuntoCorte[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
