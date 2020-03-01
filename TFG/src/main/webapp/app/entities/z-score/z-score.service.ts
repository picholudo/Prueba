import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IZScore } from 'app/shared/model/z-score.model';

type EntityResponseType = HttpResponse<IZScore>;
type EntityArrayResponseType = HttpResponse<IZScore[]>;

@Injectable({ providedIn: 'root' })
export class ZScoreService {
  public resourceUrl = SERVER_API_URL + 'api/z-scores';

  constructor(protected http: HttpClient) {}

  create(zScore: IZScore): Observable<EntityResponseType> {
    return this.http.post<IZScore>(this.resourceUrl, zScore, { observe: 'response' });
  }

  update(zScore: IZScore): Observable<EntityResponseType> {
    return this.http.put<IZScore>(this.resourceUrl, zScore, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IZScore>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IZScore[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
