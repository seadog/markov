repositories.remote << 'http://www.ibiblio.org/maven2/'

define 'Markov' do
    project.version = '0.1.0'
    package(:jar).with :manifest => { 'Main-Class' => 'uk.co.ignignokt.markov.Main' }
    test.with 'commons-collections:commons-collections:jar:3.2'
end
