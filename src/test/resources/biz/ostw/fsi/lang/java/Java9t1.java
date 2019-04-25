class Interface<T extends List> {

    public void f() {
        if ( result) {
            result = false;
            Iterator<Certificate> iteratorCert = certificates.iterator();
            while (iteratorCert.hasNext()) {
                Certificate _certificate = iteratorCert.next();
                if (_certificate.equals(certificate)) {
                    iteratorCert.remove();
                    result = true;
                    break;
                }
            }
        }
    }

    public enum systemType {
        UPG {
            @Override
            public String toString() {
                return "UPG";
            }
        }
    }
}
