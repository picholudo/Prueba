import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEstadisticaSSNeuronorma } from 'app/shared/model/estadistica-ss-neuronorma.model';

type EntityResponseType = HttpResponse<IEstadisticaSSNeuronorma>;
type EntityArrayResponseType = HttpResponse<IEstadisticaSSNeuronorma[]>;

@Injectable({ providedIn: 'root' })
export class EstadisticaSSNeuronormaService {
  public resourceUrl = SERVER_API_URL + 'api/estadistica-ss-neuronormas';

  constructor(protected http: HttpClient) {}

  create(estadisticaSSNeuronorma: IEstadisticaSSNeuronorma): Observable<EntityResponseType> {
    return this.http.post<IEstadisticaSSNeuronorma>(this.resourceUrl, estadisticaSSNeuronorma, { observe: 'response' });
  }

  update(estadisticaSSNeuronorma: IEstadisticaSSNeuronorma): Observable<EntityResponseType> {
    return this.http.put<IEstadisticaSSNeuronorma>(this.resourceUrl, estadisticaSSNeuronorma, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEstadisticaSSNeuronorma>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEstadisticaSSNeuronorma[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
