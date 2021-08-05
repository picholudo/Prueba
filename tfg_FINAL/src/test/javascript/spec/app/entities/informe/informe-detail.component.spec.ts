import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { InformeDetailComponent } from 'app/entities/informe/informe-detail.component';
import { Informe } from 'app/shared/model/informe.model';

describe('Component Tests', () => {
  describe('Informe Management Detail Component', () => {
    let comp: InformeDetailComponent;
    let fixture: ComponentFixture<InformeDetailComponent>;
    const route = ({ data: of({ informe: new Informe(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [InformeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(InformeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InformeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load informe on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.informe).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
