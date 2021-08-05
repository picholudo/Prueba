import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { EstadisticaSSNeuronormaService } from 'app/entities/estadistica-ss-neuronorma/estadistica-ss-neuronorma.service';
import { IEstadisticaSSNeuronorma, EstadisticaSSNeuronorma } from 'app/shared/model/estadistica-ss-neuronorma.model';

describe('Service Tests', () => {
  describe('EstadisticaSSNeuronorma Service', () => {
    let injector: TestBed;
    let service: EstadisticaSSNeuronormaService;
    let httpMock: HttpTestingController;
    let elemDefault: IEstadisticaSSNeuronorma;
    let expectedResult: IEstadisticaSSNeuronorma | IEstadisticaSSNeuronorma[] | boolean | null;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EstadisticaSSNeuronormaService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new EstadisticaSSNeuronorma(0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EstadisticaSSNeuronorma', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new EstadisticaSSNeuronorma())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EstadisticaSSNeuronorma', () => {
        const returnedFromService = Object.assign(
          {
            pd: 1,
            scaledScore: 1
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EstadisticaSSNeuronorma', () => {
        const returnedFromService = Object.assign(
          {
            pd: 1,
            scaledScore: 1
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query()
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a EstadisticaSSNeuronorma', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
