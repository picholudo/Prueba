import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEstadisticaTAVEC } from 'app/shared/model/estadistica-tavec.model';

type EntityResponseType = HttpResponse<IEstadisticaTAVEC>;
type EntityArrayResponseType = HttpResponse<IEstadisticaTAVEC[]>;

@Injectable({ providedIn: 'root' })
export class EstadisticaTAVECService {
  public resourceUrl = SERVER_API_URL + 'api/estadistica-tavecs';

  constructor(protected http: HttpClient) {}

  create(estadisticaTAVEC: IEstadisticaTAVEC): Observable<EntityResponseType> {
    return this.http.post<IEstadisticaTAVEC>(this.resourceUrl, estadisticaTAVEC, { observe: 'response' });
  }

  update(estadisticaTAVEC: IEstadisticaTAVEC): Observable<EntityResponseType> {
    return this.http.put<IEstadisticaTAVEC>(this.resourceUrl, estadisticaTAVEC, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEstadisticaTAVEC>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEstadisticaTAVEC[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
