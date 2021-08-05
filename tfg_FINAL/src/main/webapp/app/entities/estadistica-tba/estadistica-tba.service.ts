import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEstadisticaTBA } from 'app/shared/model/estadistica-tba.model';

type EntityResponseType = HttpResponse<IEstadisticaTBA>;
type EntityArrayResponseType = HttpResponse<IEstadisticaTBA[]>;

@Injectable({ providedIn: 'root' })
export class EstadisticaTBAService {
  public resourceUrl = SERVER_API_URL + 'api/estadistica-tbas';

  constructor(protected http: HttpClient) {}

  create(estadisticaTBA: IEstadisticaTBA): Observable<EntityResponseType> {
    return this.http.post<IEstadisticaTBA>(this.resourceUrl, estadisticaTBA, { observe: 'response' });
  }

  update(estadisticaTBA: IEstadisticaTBA): Observable<EntityResponseType> {
    return this.http.put<IEstadisticaTBA>(this.resourceUrl, estadisticaTBA, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEstadisticaTBA>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEstadisticaTBA[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
